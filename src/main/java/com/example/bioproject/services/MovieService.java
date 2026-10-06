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

    public boolean deleteMovie(Long id) {

    movieRepo.deleteById(id); //finder id, på den film der skal slettes.
    return true;
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepo.findById(id);
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
