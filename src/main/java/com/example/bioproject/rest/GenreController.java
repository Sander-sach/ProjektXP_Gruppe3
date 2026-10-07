package com.example.bioproject.rest;


import com.example.bioproject.enums.GenreEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @GetMapping
    public GenreEnum[] getAllEnum() {
        return GenreEnum.values();
    }

}
