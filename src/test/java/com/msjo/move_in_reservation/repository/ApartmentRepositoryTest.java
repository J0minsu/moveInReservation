package com.msjo.move_in_reservation.repository;

import com.msjo.move_in_reservation.domain.Apartment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApartmentRepositoryTest {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Test
    void findDistinctByDong() {


        List<String> list = new ArrayList<>();
        list = apartmentRepository.findDistinctDong();

        for(String apartment : list)
            System.out.println(apartment);



    }
}