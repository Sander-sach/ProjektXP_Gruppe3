package com.example.bioproject.entities;

import jakarta.persistence.*;

@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;
    @Column(nullable = false)
    private String name;

    public Theater() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
