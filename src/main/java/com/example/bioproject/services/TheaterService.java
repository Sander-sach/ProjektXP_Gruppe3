package com.example.bioproject.services;

import com.example.bioproject.entities.Theater;
import com.example.bioproject.repositories.TheaterRepository;

import java.util.List;

public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public List<Theater> findAllTheaters() {
        return theaterRepository.findAll();
    }

}
