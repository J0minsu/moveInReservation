package com.msjo.move_in_reservation.controller;


import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public ResponseEntity<Reservation> getReservation(HttpSession session) {

        User user = (User)session.getAttribute("loginUser");

        Reservation reservation = reservationService.findByPhoneNumber(user.getId());

        if(reservation.getId() == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(reservation);

    }

    @GetMapping("/lists")
    public ResponseEntity<List<Reservation>> getApartments(HttpSession session) {

        User user = (User)session.getAttribute("loginUser");

        List<Reservation> reservations = reservationService.findReservations(user.getApartment().getDong());

        return ResponseEntity.ok(reservations);
    }

    @PostMapping()
    public ResponseEntity<Void> createReservation(@RequestBody Reservation reservation, HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        String result = reservationService.createReservation(user, reservation.getReservationTime());

        if(result.equals("FAILURE"))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> cancelReservation(HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        String result = reservationService.cancelReservation(user.getId());

        if(result.equals("SUCCESS"))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
