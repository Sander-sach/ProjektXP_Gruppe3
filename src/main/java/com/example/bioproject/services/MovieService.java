package com.example.bioproject.services;

import com.example.bioproject.entities.Movie;
import com.example.bioproject.repositories.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    public MovieService(MovieRepo moveRepo) {
        this.movieRepo = moveRepo;
    }

    public List<Movie> findAllMovies(){
        return movieRepo.findAll();
    }

    //Removes the movie from the program without deleting it, so the statistics are kept
    public boolean removeMovie(Long id) {
        Movie movie = movieRepo.findById(id).orElse(null);

        if (movie == null) {
            return false;
        }

        movie.setActive(false);
        movieRepo.save(movie);
        return true;
    }
}