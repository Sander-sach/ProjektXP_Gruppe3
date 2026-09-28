package com.example.bioproject.repositories;

import com.example.bioproject.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

}