package com.example.bioproject.services;

import com.example.bioproject.entities.Movie;
import com.example.bioproject.repositories.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    public MovieService(MovieRepo moveRepo) {
        this.movieRepo = moveRepo;
    }

    public List<Movie> findAllMovies(){
        return movieRepo.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {return movieRepo.findById(id);}


}
