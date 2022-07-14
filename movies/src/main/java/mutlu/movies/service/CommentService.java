package mutlu.movies.service;

import mutlu.movies.entity.Comment;
import mutlu.movies.repository.CommentRepository;
import mutlu.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    public Comment create(Comment request) {
        var user = userRepository.findById(request.getUser().getUserId()).orElseThrow();
        if (!user.isPremium()) {
            throw new RuntimeException("Adding comments requires to be paid user.");
        }
        request.setUser(user);
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
