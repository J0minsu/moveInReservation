package com.msjo.move_in_reservation.service;

import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.dto.ForReservationList;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    Reservation findByPhoneNumber(String phoneNumber);

    List<ForReservationList> findReservations();

    void cancelReservation(String phoneNumber);
}
