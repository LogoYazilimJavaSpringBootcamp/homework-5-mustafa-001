package mutlu.movies.service;

import mutlu.movies.entity.Comment;
import mutlu.movies.entity.Movie;
import mutlu.movies.repository.CommentRepository;
import mutlu.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Collection;

@Service
public class MovieService {
private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CommentRepository commentRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
    }

    public Movie create(Movie request) {
        return movieRepository.save(request);
    }

    public Collection<Movie> getByUserId(Long userId) {
        return movieRepository.findAll();
    }

    public Movie update(Movie request, Long movieId) {
        return request;
    }

    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public Comment addComment(Comment comment, Long movieId) {
        var movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            throw new InvalidParameterException();
        }
        comment.setMovie(movie.get());
        var c = commentRepository.save(comment);
        movie.get().getCommentSet().add(c);
        return c;
    }

    public void removeComment(Long commentId, Long movieId){
        var movie = movieRepository.findById(movieId);
        var comment = commentRepository.findById(commentId);
        if (movie.isEmpty() || comment.isEmpty()){
            throw new InvalidParameterException();
        }
        commentRepository.delete(comment.get());
        movie.get().getCommentSet().remove(comment.get());

}
}
