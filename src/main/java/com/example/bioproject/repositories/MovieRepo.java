package com.example.bioproject.repositories;

import com.example.bioproject.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

}