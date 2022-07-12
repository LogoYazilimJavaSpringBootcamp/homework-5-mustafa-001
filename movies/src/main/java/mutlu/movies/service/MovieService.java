package mutlu.movies.service;

import mutlu.movies.entity.Comment;
import mutlu.movies.entity.Movie;
import mutlu.movies.repository.CommentRepository;
import mutlu.movies.repository.MovieRepository;
import mutlu.movies.repository.UserRepository;
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

    @Autowired
    public MovieService(MovieRepository movieRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Movie create(Movie request) {
        var user = userRepository.findByUsername(request.getUser().getUsername()).orElseThrow();
        System.out.println("Adding movie, user is: "+ user.getUsername());
        Integer movieCount = movieRepository.numberOfMoviesByUserName(user.getUsername());
        System.out.println("Movies by this user: " + movieCount);
        if (movieCount <= 3 || user.isPremium()){
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

        var user = userRepository.findByUsername(comment.getUser().getUsername()).orElseThrow();
        if (!user.isPremium()){
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
