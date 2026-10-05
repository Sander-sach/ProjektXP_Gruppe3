package com.example.bioproject.entities;

import com.example.bioproject.enums.AgeLimitEnum;
import com.example.bioproject.enums.GenreEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean active = true; //false = removed from the program


    //der skal også være dato - så man kan oprette data.

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
public void setMovieTitle(String movieTitle){
        this.movieTitle=movieTitle;
}
public void setDescription(String description){
        this.description=description;
}
public void setGenre(GenreEnum genre){
        this.genre=genre;
}
public void setAgeLimit(AgeLimitEnum ageLimit){
        this.ageLimit=ageLimit;
}
public void setDuration(int duration){
        this.duration=duration;
}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}