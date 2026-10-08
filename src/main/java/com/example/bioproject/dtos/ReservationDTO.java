package com.example.bioproject.dtos;

public record ReservationDTO(
        Long screeningId,
        String customerName,
        String customerMobile,
        Integer numberOfPeople
) {}
