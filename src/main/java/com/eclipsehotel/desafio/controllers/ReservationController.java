package com.eclipsehotel.desafio.controllers;

import com.eclipsehotel.desafio.models.Reservation;
import com.eclipsehotel.desafio.models.ReservationStatus;
import com.eclipsehotel.desafio.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @PutMapping("/{id}")
    public Reservation closeReservation(@PathVariable Long id, @RequestParam ReservationStatus status) {
        return reservationService.closeReservation(id, status);
    }

    @GetMapping("/date-range")
    public List<Reservation> getReservationsByDateRange(
            @RequestParam("start") String startDate,
            @RequestParam("end") String endDate) {

        // Defina o formato de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Remove espaços e novas linhas dos inputs
        startDate = startDate.trim();
        endDate = endDate.trim();

        // Converta Strings para LocalDate
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        // Converta LocalDate para LocalDateTime (começo do dia)
        LocalDateTime startDateTime = startLocalDate.atStartOfDay();
        LocalDateTime endDateTime = endLocalDate.atStartOfDay().plusDays(1); // Ajuste para incluir o final do dia

        // Chame o serviço com os parâmetros ajustados
        return reservationService.getReservationsByDateRange(startLocalDate, endLocalDate);
    }


    @GetMapping("/occupied-rooms")
    public List<Reservation> getOccupiedRooms() {
        return reservationService.getOccupiedRooms();
    }
}
