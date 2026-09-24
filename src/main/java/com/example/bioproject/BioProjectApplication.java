package com.example.bioproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("com.example.bioproject.entities")
public class BioProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BioProjectApplication.class, args);
    }

}
