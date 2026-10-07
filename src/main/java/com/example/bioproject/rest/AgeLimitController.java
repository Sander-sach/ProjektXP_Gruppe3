package com.example.bioproject.rest;

import com.example.bioproject.enums.AgeLimitEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AgeLimitController {

    @GetMapping("/agelimit")
    public AgeLimitEnum[] getAllEnum() {
        return AgeLimitEnum.values();
    }

}