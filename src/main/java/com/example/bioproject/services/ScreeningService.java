package com.example.bioproject.services;

import com.example.bioproject.entities.Screening;
import com.example.bioproject.repositories.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    // window.location.href

    //dockerfile
    //compose.yml fil
    //Database service på compose
    //Application service på compose
        //application skal spændes op på en container og database på en container og så skal de snakke sammen.
    //.github/workflows/ci.yml


}
