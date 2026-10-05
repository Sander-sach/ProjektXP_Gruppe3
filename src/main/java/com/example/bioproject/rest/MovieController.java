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
    //Henter alle film
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }
    //Opret film
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }
    @DeleteMapping("/movies/{id}")
    public boolean deleteMovie(@PathVariable Long id){
        return movieService.deleteMovie(id);
    }
}