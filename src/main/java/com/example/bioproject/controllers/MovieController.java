package com.example.bioproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

    @GetMapping("/get-movies")
    public String movieOverviewPage2(){
        return "movie_overview";
    }
}
