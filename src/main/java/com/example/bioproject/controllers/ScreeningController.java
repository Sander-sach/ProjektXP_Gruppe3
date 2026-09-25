package com.example.bioproject.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ScreeningController {

    @GetMapping("/screening/create-form")
    public String createScreening(Model model) {

        model.addAttribute("movies", movieService.findAllMovies());
        model.addAttribute("theaters", theaterService.findAllTheaters());

        return "create_screening_form";
    }



}
