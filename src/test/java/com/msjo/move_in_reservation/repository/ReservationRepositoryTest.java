package com.msjo.move_in_reservation.repository;

import com.msjo.move_in_reservation.domain.Apartment;
import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    void test() {

        User user = new User();
        user.setId("01012345678");
        user.setPassword("msjomsjo");
        user.setName("더미더미");
        user.setApartment(apartmentRepository.findById(2).orElse(new Apartment()));

        userRepository.save(user);

        System.out.println(userRepository.findById("01012345678"));

        user.setPassword("");

        Reservation reservation = new Reservation();
        reservation.setReservationTime(LocalDate.now());
        reservation.setUser(user);

        System.out.println(reservationRepository.save(reservation));

    }

    @Test
    void optionalTest() {

        Reservation reservation = reservationRepository.findByUserId("01055253786").orElse(new Reservation());

        System.out.println(reservation);

    }

    @Test
    void queryTest() {

        Reservation reservation = reservationRepository.findReservationByUserApartmentDongAndReservationTime(
                                                    "101", LocalDate.parse("2021-05-27"))
                                                    .orElse(new Reservation());

        System.out.println(reservation);

    }


    @Test
    void parsingTest() {
        String path = "../move_in_reservation/documents/csv/seed-data.csv";

        System.out.println(apartmentRepository.count());

        List<List<String>> list = new ArrayList<>();
        File csv = new File(path);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8");
            String line;

            while ((line = br.readLine()) != null) {
                String[] token = line.split(",");
                List<String> tempList = new ArrayList<String>(Arrays.asList(token));
                list.add(tempList);
            }

        } catch (Exception e) {

        }

        for (int i = 1; i < list.size(); i++) {

            List<String> lists = list.get(i);

            for(String string : lists)
                System.out.println(string);

        }
    }
}