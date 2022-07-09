package mutlu.movies.controller;


import mutlu.movies.entity.Comment;
import mutlu.movies.entity.Movie;
import mutlu.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{userId}")
    public Collection<Movie> getByUserId(@PathVariable Long userId) {
        return movieService.getByUserId(userId);
    }

    @PostMapping("/{movieId}")
    public Comment addComment(@RequestBody Comment comment, @PathVariable Long movieId) {
        return movieService.addComment(comment, movieId);
    }

    @DeleteMapping("/{movieId}/{commentId}")
    public void removeComment(@PathVariable Long movieId, @PathVariable Long commentId) {
        movieService.removeComment(commentId, movieId);
    }

    @PostMapping
    public Movie add(@RequestBody Movie request) {
        return movieService.create(request);
    }

    @PutMapping("/{movieId}")
    public Movie update(@RequestBody Movie request, @PathVariable Long movieId) {
        return movieService.update(request, movieId);
    }

    @DeleteMapping("/{movieId}")
    public void delete(@PathVariable Long movieId) {
        movieService.delete(movieId);
    }
}
