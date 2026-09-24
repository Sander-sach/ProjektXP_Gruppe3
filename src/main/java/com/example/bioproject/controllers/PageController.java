package com.example.bioproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/movie-overview")
    public String movieOverviewPage() {
        return "movie_overview";
    }

}
