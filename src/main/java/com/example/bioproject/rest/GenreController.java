package com.example.bioproject.rest;


import com.example.bioproject.enums.GenreEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GenreController {

    @GetMapping("/genres")
    public GenreEnum[] getAllEnum() {
        return GenreEnum.values();
    }

}
