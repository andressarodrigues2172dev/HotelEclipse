package com.eclipsehotel.desafio.repositorys;

import com.eclipsehotel.desafio.models.Reservation;
import com.eclipsehotel.desafio.models.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCheckinBetween(LocalDateTime start, LocalDateTime end);
    List<Reservation> findByStatus(ReservationStatus status);
}
