package com.example.bioproject.rest;

import com.example.bioproject.entities.Screening;
import com.example.bioproject.services.ScreeningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/screenings")
    public List<Screening> getAllScreenings() {
        return screeningService.getAllScreenings();
    }



}
