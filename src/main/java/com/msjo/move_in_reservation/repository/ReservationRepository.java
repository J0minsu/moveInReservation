package com.msjo.move_in_reservation.repository;

import com.msjo.move_in_reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
