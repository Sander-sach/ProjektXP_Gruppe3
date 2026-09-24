package com.example.bioproject.controllers;

import com.example.bioproject.enums.GenreEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {



    @GetMapping("/movie-overview")
    public String movieOverviewPage(Model model) {

        model.addAttribute("genre", GenreEnum.values());

        return "movie_overview";
    }

}
