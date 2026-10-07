package com.example.bioproject.rest;

import com.example.bioproject.enums.AgeLimitEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agelimit")
public class AgeLimitController {

    @GetMapping
    public AgeLimitEnum[] getAllEnum() {
        return AgeLimitEnum.values();
    }

}