package com.example.bioproject;

import com.example.bioproject.entities.Movie;
import com.example.bioproject.entities.Screening;
import com.example.bioproject.entities.Theater;
import com.example.bioproject.repositories.ScreeningRepository;
import com.example.bioproject.services.ScreeningService;

import org.apiguardian.api.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScreeningServiceTest {

    //Arrange, Act, Assert

    //Valid screening	            =>   Screening is saved
    //Theater already occupied	    =>   Screening is rejected
    //Screenings do not overlap	    =>   Screening is saved
    //Multiple screenings overlap	=>   Conflicting batch is rejected

    //Note: This assumes getDuration() returns an integer representing minutes, your Screening constructor accepts those three arguments, and your service throws IllegalArgumentException for conflicts. Adjust those parts to match your actual implementation.
    //Your JavaScript already checks theater availability, but that is primarily for the user experience.
    //Your service must perform the same validation independently.
    //Otherwise, someone could bypass the JavaScript and send a POST request directly to your REST API, creating overlapping screenings.
    //The unit tests help ensure that your service rejects those invalid requests regardless of how they reach the backend.

    private ScreeningRepository screeningRepository;
    private ScreeningService screeningService;

    private Movie movie;
    private Theater theater;

    @BeforeEach
    void setUp() {

        screeningRepository = mock(ScreeningRepository.class);

        screeningService = new ScreeningService(screeningRepository);

        movie = mock(Movie.class);
        theater = mock(Theater.class);

        when(movie.getDuration()).thenReturn(120);
        when(theater.getId()).thenReturn(1L);
    }

    @Test
    void shouldCreateScreeningWhenTheaterIsAvailable() {

        // Arrange
        LocalDateTime startTime =
                LocalDateTime.of(2026, 10, 1, 18, 0);

        when(screeningRepository.findByTheaterId(1L))
                .thenReturn(List.of());

        // Act
        screeningService.createScreening(movie, theater, startTime);

        // Assert
        verify(screeningRepository, times(1))
                .save(any(Screening.class));
    }

    @Test
    void shouldRejectScreeningWhenTheaterIsOccupied() {

        // Arrange
        LocalDateTime existingStart =
                LocalDateTime.of(2026, 10, 1, 18, 0);

        Screening existingScreening =
                new Screening(movie, theater, existingStart);

        when(screeningRepository.findByTheaterId(1L))
                .thenReturn(List.of(existingScreening));

        LocalDateTime newStart =
                LocalDateTime.of(2026, 10, 1, 19, 0);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> screeningService.createScreening(
                        movie,
                        theater,
                        newStart
                )
        );

        verify(screeningRepository, never())
                .save(any(Screening.class));
    }

    @Test
    void shouldAllowScreeningWhenPreviousScreeningHasEnded() {

        // Arrange
        LocalDateTime existingStart =
                LocalDateTime.of(2026, 10, 1, 18, 0);

        Screening existingScreening =
                new Screening(movie, theater, existingStart);

        when(screeningRepository.findByTheaterId(1L))
                .thenReturn(List.of(existingScreening));

        // Existing screening ends at 20:00
        LocalDateTime newStart =
                LocalDateTime.of(2026, 10, 1, 20, 0);

        // Act
        screeningService.createScreening(movie, theater, newStart);

        // Assert
        verify(screeningRepository, times(1))
                .save(any(Screening.class));
    }

}