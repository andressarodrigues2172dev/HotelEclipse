package com.eclipsehotel.desafio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "reservations")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

    public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime checkin;
    private LocalDateTime checkout;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
