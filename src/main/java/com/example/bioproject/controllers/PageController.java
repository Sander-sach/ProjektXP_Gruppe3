package com.example.bioproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class PageController {

    @GetMapping("/movie-overview")
    public String movieOverviewPage() {
        return "/movie_overview";
    }

}
