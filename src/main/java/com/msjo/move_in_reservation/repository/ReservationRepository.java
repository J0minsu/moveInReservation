package com.msjo.move_in_reservation.repository;

import com.msjo.move_in_reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

}
