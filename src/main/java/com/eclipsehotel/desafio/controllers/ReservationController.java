package com.eclipsehotel.desafio.controllers;

import com.eclipsehotel.desafio.models.Reservation;
import com.eclipsehotel.desafio.models.ReservationStatus;
import com.eclipsehotel.desafio.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {



    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }


    @PutMapping("/{id}/close/{status}")
    public Reservation closeReservation(@PathVariable Long id, @PathVariable ReservationStatus status) {
        return reservationService.closeReservation(id, status);
    }


    @GetMapping("/date-range")
    public List<Reservation> getReservationsByDateRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return reservationService.getReservationsByDateRange(start, end);
    }

    @GetMapping("/occupied")
    public List<Reservation> getOccupiedRooms() {
        return reservationService.getOccupiedRooms();
    }
}
