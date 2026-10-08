package com.example.bioproject.rest;

import com.example.bioproject.entities.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok("OK");
    }
}
