package com.example.bioproject.rest;

import com.example.bioproject.dtos.CreateScreeningDTO;
import com.example.bioproject.entities.Screening;
import com.example.bioproject.services.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    //Gets all screenings in a list
    @GetMapping
    public List<Screening> getAllScreenings() {
        return screeningService.getAllScreenings();
    }

    //Sends all screenings as list from frontend to service for saving
    @PostMapping("/batch")
    public ResponseEntity<Void> createScreenings(@RequestBody List<CreateScreeningDTO> screenings) throws Exception {

        screeningService.createScreenings(screenings);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
