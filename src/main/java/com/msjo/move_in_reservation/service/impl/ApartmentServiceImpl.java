package com.msjo.move_in_reservation.service.impl;

import com.msjo.move_in_reservation.domain.Apartment;
import com.msjo.move_in_reservation.repository.ApartmentRepository;
import com.msjo.move_in_reservation.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Override
    public List<String> findDongList() {

        List<String> dongList = new ArrayList<>();

        dongList = apartmentRepository.findDistinctDong();

        return dongList;
    }


}
