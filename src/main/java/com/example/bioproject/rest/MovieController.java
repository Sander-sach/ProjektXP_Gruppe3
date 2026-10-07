package com.example.bioproject.rest;

import com.example.bioproject.entities.Movie;
import com.example.bioproject.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    //Henter alle film
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }
    //Opret film
    @PostMapping("/create")
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMovie(@PathVariable Long id){
        return movieService.deleteMovie(id);
    }


    @GetMapping("/test")
    public String test() {
        return "Controller works!";
    }

}