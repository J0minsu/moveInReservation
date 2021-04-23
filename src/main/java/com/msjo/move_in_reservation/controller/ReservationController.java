package com.msjo.move_in_reservation.controller;


import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.dto.ForReservationList;
import com.msjo.move_in_reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<Reservation> getReservation(HttpSession session) {

        User user = (User)session.getAttribute("loginUser");

        Reservation reservation = reservationService.findByPhoneNumber(user.getPhoneNumber());

        if(reservation.getUser() == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(reservation);

    }

    @GetMapping("/lists")
    public ResponseEntity<List<ForReservationList>> getApartments() {

        List<ForReservationList> reservations = reservationService.findReservations();

        return ResponseEntity.ok().body(reservations);
    }

    @DeleteMapping()
    public ResponseEntity<Void> cancelReservation(HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        reservationService.cancelReservation(user.getPhoneNumber());

        return ResponseEntity.ok().build();
    }
}
