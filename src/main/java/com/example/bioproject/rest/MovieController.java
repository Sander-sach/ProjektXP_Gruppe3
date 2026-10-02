package com.example.bioproject.rest;

import com.example.bioproject.entities.Movie;
import com.example.bioproject.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @DeleteMapping("/movies/{id}")
    public boolean removeMovie(@PathVariable("id") Long id) {
        return movieService.removeMovie(id);
    }
}