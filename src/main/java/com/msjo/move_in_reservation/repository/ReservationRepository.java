package com.msjo.move_in_reservation.repository;

import com.msjo.move_in_reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findReservationByUserApartmentDongAndReservationTime(String dong, LocalDate reservationTime);

    Optional<Reservation> findByUserId(String userId);

    ArrayList<Reservation> findReservationsByUserApartmentDongOrderByReservationTimeAsc(String dong);

}
