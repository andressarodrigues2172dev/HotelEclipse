package com.eclipsehotel.desafio.services;


import com.eclipsehotel.desafio.models.Reservation;
import com.eclipsehotel.desafio.models.ReservationStatus;
import com.eclipsehotel.desafio.repositorys.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.SCHEDULED);
        return reservationRepository.save(reservation);
    }

    public Reservation closeReservation(Long id, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null && (status == ReservationStatus.FINISHED || status == ReservationStatus.CANCELED || status == ReservationStatus.ABSENCE)) {
            reservation.setStatus(status);
            return reservationRepository.save(reservation);
        } else {
            return null;
        }
    }

    public List<Reservation> getReservationsByDateRange(LocalDateTime start, LocalDateTime end) {
        return reservationRepository.findByCheckinBetween(start, end);
    }

    public List<Reservation> getOccupiedRooms() {
        return reservationRepository.findByStatus(ReservationStatus.IN_USE);
    }
}
