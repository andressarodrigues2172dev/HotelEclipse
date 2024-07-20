package com.eclipsehotel.desafio.services;

import com.eclipsehotel.desafio.models.Reservation;
import com.eclipsehotel.desafio.models.ReservationStatus;
import com.eclipsehotel.desafio.repositorys.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        logger.info("Fetching all reservations");
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        logger.info("Fetching reservation with id: {}", id);
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.SCHEDULED);
        logger.info("Creating reservation for customer id: {} and room id: {}", reservation.getCustomer().getId(), reservation.getRoom().getId());
        return reservationRepository.save(reservation);
    }

    public Reservation closeReservation(Long id, ReservationStatus status) {
        logger.info("Closing reservation with id: {} and status: {}", id, status);
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null && (status == ReservationStatus.FINISHED || status == ReservationStatus.CANCELED || status == ReservationStatus.ABSENCE)) {
            reservation.setStatus(status);
            return reservationRepository.save(reservation);
        } else {
            logger.error("Invalid status or reservation not found for id: {}", id);
            return null;
        }
    }

    public List<Reservation> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        // Converta LocalDate para LocalDateTime no serviço, se necessário
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atStartOfDay().plusDays(1); // Ajuste para incluir o final do dia

        // Implemente a lógica de busca usando LocalDateTime
        return reservationRepository.findByCheckinBetween(startDateTime, endDateTime);
    }
    public List<Reservation> getOccupiedRooms() {
        logger.info("Fetching occupied rooms");
        return reservationRepository.findByStatus(ReservationStatus.IN_USE);
    }
}
