package mutlu.movies.controller;


import mutlu.movies.entity.Comment;
import mutlu.movies.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{movieId}")
    public List<Comment> getByMovieId(@PathVariable Long movieId) {
        return commentService.getByMovieId(movieId);
    }

    @PostMapping
    public Comment add(@RequestBody Comment request) {
        return commentService.create(request);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }
}
