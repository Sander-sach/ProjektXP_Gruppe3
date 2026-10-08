package com.example.bioproject.services;

import com.example.bioproject.dtos.ReservationDTO;
import com.example.bioproject.entities.Reservation;
import com.example.bioproject.entities.Screening;
import com.example.bioproject.repositories.ReservationRepository;
import com.example.bioproject.repositories.ScreeningRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            ScreeningRepository screeningRepository) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(ReservationDTO dto) {

        //debugging
        System.out.println("createReservation called with: " + dto);
        Screening screening = screeningRepository.findById(dto.screeningId())
                .orElseThrow(() -> new RuntimeException("Screening not found: " + dto.screeningId()));

        Reservation reservation = new Reservation();
        reservation.setScreening(screening);
        reservation.setCustomerName(dto.customerName());
        reservation.setCustomerMobile(dto.customerMobile());
        reservation.setNumberOfPeople(dto.numberOfPeople());

        return reservationRepository.save(reservation);
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