package com.example.bioproject.dtos;

import java.time.LocalDateTime;

public record CreateScreeningDTO(
        Long movieId,
        Long theaterId,
        LocalDateTime startTime
    ) {}
