package com.example.bioproject.services;

import com.example.bioproject.entities.Movie;
import com.example.bioproject.repositories.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> findAllMovies(){
        return movieRepo.findAll();
    }
    public Movie createMovie(Movie movie){
        return movieRepo.save(movie);//Gemmer den nye film, i vores database.

    }
    public Movie updateMovie(Long id, Movie movie){
        //finder den film, vi vil ændre.
        Movie existingMovie = movieRepo.findById(id)
                .orElseThrow();
        existingMovie.setMovieTitle(movie.getMovieTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setAgeLimit(movie.getAgeLimit());
        existingMovie.setDuration(movie.getDuration());

        //alle de oplysninger der kan ændres
        return movieRepo.save(existingMovie); //gemmer ændringerne i databasen.
    }
  public void deleteMovie(Long id) {
      movieRepo.deleteById(id); //finder id, på den film der skal slettes.
  }
}
