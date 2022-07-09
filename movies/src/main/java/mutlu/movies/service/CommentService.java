package mutlu.movies.service;

import mutlu.movies.entity.Comment;
import mutlu.movies.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository userRepository) {
        this.commentRepository = userRepository;
    }


    public Comment create(Comment request) {
        return commentRepository.save(request);
    }

    public Optional<Comment> getByCommentId(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getByUsername(String username) {
        return commentRepository.findByUser_Username(username);
    }

    public List<Comment> getByMovieId(Long movieId) {
        return commentRepository.findByMovie_MovieId(movieId);
    }

    public Comment update(Comment request, Long movieId) {
        return request;
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
