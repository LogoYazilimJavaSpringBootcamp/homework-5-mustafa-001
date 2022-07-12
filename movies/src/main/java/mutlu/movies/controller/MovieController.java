package mutlu.movies.controller;


import mutlu.movies.entity.Comment;
import mutlu.movies.entity.Movie;
import mutlu.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService ) {
        this.movieService = movieService;
    }

    @GetMapping("/user/{username}")
    public Collection<Movie> getByUserId(@PathVariable String username) {
        return movieService.getByUsername(username);
    }

    @GetMapping("/{movieId}")
    public Optional<Movie> getByMovieId(@PathVariable Long movieId) {
        return movieService.getById(movieId);
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
