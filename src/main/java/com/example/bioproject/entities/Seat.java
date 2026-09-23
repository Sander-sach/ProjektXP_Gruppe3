package com.example.bioproject.entities;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;
    @Column(nullable = false)
    private char rowLabel;
    @Column(nullable = false)
    private int seatNumber;
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    public Seat() {}

    public Long getId() {
        return id;
    }

    public char getRow() {
        return rowLabel;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Theater getTheater() {
        return theater;
    }
}
