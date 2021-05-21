package com.msjo.move_in_reservation.controller;

import com.msjo.move_in_reservation.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/apartments")
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/dongs")
    public ResponseEntity<List<String>> getApartments() {


        List<String> dongList = apartmentService.findDongList();

        return ResponseEntity.ok().body(dongList);
    }

}
