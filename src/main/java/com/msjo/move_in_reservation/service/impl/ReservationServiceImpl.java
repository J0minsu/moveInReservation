package com.msjo.move_in_reservation.service.impl;

import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.dto.ForReservationList;
import com.msjo.move_in_reservation.repository.ReservationRepository;
import com.msjo.move_in_reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation findByPhoneNumber(String phoneNumber) {

        Reservation reservation = reservationRepository.findByPhoneNumber(phoneNumber).orElse(new Reservation());

        return reservation;
    }

    @Override
    public List<ForReservationList> findReservations() {

        List<Reservation> reservations = reservationRepository.findAll();

        List<ForReservationList> processingReservations = new ArrayList<>();

        for(Reservation reservation : reservations) {

            processingReservations.add(
                    new ForReservationList(reservation.getReservationTime(), reservation.getUser().getApartment()));

        }
        return processingReservations;
    }

    @Override
    public void cancelReservation(String phoneNumber) {

        reservationRepository.deleteByPhoneNumber(phoneNumber);

    }
}
