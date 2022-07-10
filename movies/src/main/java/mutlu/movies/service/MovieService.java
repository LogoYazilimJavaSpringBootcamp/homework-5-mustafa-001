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
    private UserRepository userRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Movie create(Movie request) {
        return movieRepository.save(request);
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

        var user = userRepository.findByUsername(comment.getUser().getUsername()).get();
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
