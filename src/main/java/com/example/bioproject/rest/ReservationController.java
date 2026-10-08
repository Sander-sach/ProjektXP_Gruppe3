package com.example.bioproject.rest;

import com.example.bioproject.dtos.ReservationDTO;
import com.example.bioproject.entities.Reservation;
import com.example.bioproject.services.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationDTO reservation) {
        return reservationService.createReservation(reservation);
    }
}
