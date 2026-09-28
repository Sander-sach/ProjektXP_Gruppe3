package com.example.bioproject.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;
    @Column(nullable = false)
    private LocalDateTime startTime;

    public Screening() {}

    public Screening(Movie movie, Theater theater, LocalDateTime startTime) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
