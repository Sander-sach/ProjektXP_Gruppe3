package com.example.bioproject.entities;

import com.example.bioproject.enums.AgeLimitEnum;
import com.example.bioproject.enums.GenreEnum;
import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    @Column(nullable = false)
    private String movieTitle;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeLimitEnum ageLimit;
    //private List cast; //Need to figure out how to handle cast while using 3NF
    @Column(nullable = false)
    private int duration; //In minutes

    public Movie() {}

    public Long getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getDescription() {
        return description;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public AgeLimitEnum getAgeLimit() {
        return ageLimit;
    }

    public int getDuration() {
        return duration;
    }
}
