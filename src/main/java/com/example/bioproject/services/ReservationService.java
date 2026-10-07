package com.example.bioproject.services;

import com.example.bioproject.entities.Reservation;
import com.example.bioproject.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public Reservation updateReservation(long id, Reservation reservation){
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow();
        existingReservation.setCustomerName(reservation.getCustomerName());
        existingReservation.setCustomerMobile(reservation.getCustomerMobile());
        existingReservation.setNumberOfPeople(reservation.getNumberOfPeople());


        return reservationRepository.save(existingReservation);
    }
    public void deleteReservation(long id){
        reservationRepository.deleteById(id);
    }
}