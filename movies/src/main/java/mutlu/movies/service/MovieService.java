package mutlu.movies.service;

import mutlu.movies.dto.EmailDto;
import mutlu.movies.entity.Comment;
import mutlu.movies.entity.Movie;
import mutlu.movies.repository.CommentRepository;
import mutlu.movies.repository.MovieRepository;
import mutlu.movies.repository.UserRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository, CommentRepository commentRepository, UserRepository userRepository, AmqpTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Movie create(Movie request) {
        var user = userRepository.findById(request.getUser().getUserId()).orElseThrow();
        System.out.println("Adding movie, user is: " + user);
        Integer movieCount = movieRepository.numberOfMoviesByUserId(user.getUserId());
        System.out.println("Movies by this user: " + movieCount);
        if (movieCount <= 3 || user.isPremium()) {
            userRepository.findAll().forEach(registeredUser -> {
                try {
                    rabbitTemplate.convertAndSend("movies.email", new EmailDto(registeredUser.getEmail(), registeredUser.getUsername(), request.getName(), "Film hakkında açıklama."));
                } catch (Exception c) {
                    System.out.println("RabbitMQ connection refused. Continuing.");
                }
            });
            return movieRepository.save(request);


        } else {
            throw new RuntimeException("Adding more than 3 movies requires to be paid user.");
        }
    }

    public Collection<Movie> getByUsername(String username) {
        return movieRepository.findAll();
    }

    public Optional<Movie> getById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Movie update(Movie request, Long movieId) {
        return request;
    }

    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public Comment addComment(Comment comment, Long movieId) {

        var user = userRepository.findById(comment.getUser().getUserId()).orElseThrow();
        if (!user.isPremium()) {
            throw new RuntimeException("Adding comments requires to be paid user.");
        }
        comment.setUser(user);
        var movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            throw new InvalidParameterException();
        }
        comment.setMovie(movie.get());
        var c = commentRepository.save(comment);
        movie.get().getCommentList().add(c);
        return c;
    }

    public void removeComment(Long commentId, Long movieId) {
        var movie = movieRepository.findById(movieId);
        var comment = commentRepository.findById(commentId);
        if (movie.isEmpty() || comment.isEmpty()) {
            throw new InvalidParameterException();
        }
        commentRepository.delete(comment.get());
        movie.get().getCommentList().remove(comment.get());

    }
}
