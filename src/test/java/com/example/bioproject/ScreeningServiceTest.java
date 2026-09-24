package com.example.bioproject;

import com.example.bioproject.entities.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScreeningServiceTest {

    //Arrange, Act, Assert

    //Unneccesary data can be null because it isnt persisting to the database

    //Selected date has screenings -> include
    //Other dates also have screenings -> dont include them
    //Selected date has no screenings -> return an empty list

    private ScreeningRepository screeningRepository;
    private ScreeningService screeningService;

    //Runs this before each test
    @BeforeEach
    void setUp() {
        screeningRepository = mock(ScreeningRepository.class);
        screeningService = new ScreeningService(screeningRepository);
    }


    @Test
    void shouldReturnScreeningsForSelectedDate() {

        // Arrange
        LocalDate selectedDate = LocalDate.of(2026, 9, 24);

        LocalDateTime startOfDay = selectedDate.atStartOfDay();
        LocalDateTime startOfNextDay = selectedDate.plusDays(1).atStartOfDay();

        Screening screening = new Screening(
                null,
                null,
                LocalDateTime.of(2026, 9, 24, 18, 30)
        );

        when(screeningRepository.findByStartTimeBetween(
                startOfDay,
                startOfNextDay
        )).thenReturn(List.of(screening));


        // Act
        List<Screening> result =
                screeningService.getScreeningsByDate(selectedDate);


        // Assert
        assertEquals(1, result.size());
        assertEquals(screening, result.getFirst());
    }


    @Test
    void shouldReturnAllScreeningsForSelectedDate() {

        // Arrange
        LocalDate selectedDate = LocalDate.of(2026, 9, 24);

        LocalDateTime startOfDay = selectedDate.atStartOfDay();
        LocalDateTime startOfNextDay = selectedDate.plusDays(1).atStartOfDay();

        Screening screening1 = new Screening(
                null,
                null,
                LocalDateTime.of(2026, 9, 24, 12, 30)
        );

        Screening screening2 = new Screening(
                null,
                null,
                LocalDateTime.of(2026, 9, 24, 18, 30)
        );

        when(screeningRepository.findByStartTimeBetween(
                startOfDay,
                startOfNextDay
        )).thenReturn(List.of(screening1, screening2));


        // Act
        List<Screening> result =
                screeningService.getScreeningsByDate(selectedDate);


        // Assert
        assertEquals(2, result.size());
        assertEquals(List.of(screening1, screening2), result);
    }


    @Test
    void shouldReturnEmptyListWhenNoScreeningsExist() {

        // Arrange
        LocalDate selectedDate = LocalDate.of(2026, 9, 24);

        LocalDateTime startOfDay = selectedDate.atStartOfDay();
        LocalDateTime startOfNextDay = selectedDate.plusDays(1).atStartOfDay();

        when(screeningRepository.findByStartTimeBetween(
                startOfDay,
                startOfNextDay
        )).thenReturn(List.of());


        // Act
        List<Screening> result =
                screeningService.getScreeningsByDate(selectedDate);


        // Assert
        assertEquals(0, result.size());
    }


}