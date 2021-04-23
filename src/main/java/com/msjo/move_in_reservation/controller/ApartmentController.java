package com.msjo.move_in_reservation.controller;

import com.msjo.move_in_reservation.domain.Apartment;
import com.msjo.move_in_reservation.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/apartments")
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/dongs")
    public ResponseEntity<List<String>> getApartments() {

        List<String> dongList = apartmentService.findDongList();

        return ResponseEntity.ok().body(dongList);
    }

}
