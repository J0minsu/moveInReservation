package com.msjo.move_in_reservation;

import com.msjo.move_in_reservation.domain.Apartment;
import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.repository.ApartmentRepository;
import com.msjo.move_in_reservation.repository.ReservationRepository;
import com.msjo.move_in_reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }

    @Bean
    @Transactional
    CommandLineRunner runner() {
        return args -> {
            String path = "../move_in_reservation/documents/csv/seed-data.csv";
            StringBuilder phoneNumberBuilder = new StringBuilder();
            StringBuilder nameBuilder = new StringBuilder();
            String dummyPassword = "1111";

        /* START For test dummy data save */
            if (apartmentRepository.count() == 0) {

            /* START Apartment csv file read and save on DB */
                List<List<String>> list = new ArrayList<>();
                File csv = new File(path);
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(csv));
                    Charset.forName("UTF-8");
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] token = line.split(",");
                        List<String> tempList = new ArrayList<>(Arrays.asList(token));
                        list.add(tempList);
                    }

                } catch (Exception e) {

                }

                for (int i = 1; i < list.size(); i++) {

                    List<String> lists = list.get(i);
                    apartmentRepository.save(new Apartment(lists.get(0), lists.get(1)));
                }

            /* FINISH Apartment csv file read and save on DB */

            /* START Making User dummy data */
                List<Apartment> apartmentList = apartmentRepository.findAll();

                for(Apartment apartment : apartmentList) {
                    phoneNumberBuilder.append("10dummy" + apartment.getId());
                    nameBuilder.append("DUMMY_" + apartment.getId());
                    userRepository.save(new User(phoneNumberBuilder.toString(),
                                                 passwordEncoder.encode(dummyPassword),
                                                 nameBuilder.toString(),
                                                 apartment));
                    phoneNumberBuilder.delete(0, phoneNumberBuilder.length());
                    nameBuilder.delete(0, nameBuilder.length());
                }
            /* FINISH Making User dummy data */

            /* START Making User dummy data */
                List<User> userList = userRepository.findAll();

                for(int i = 0; i < userList.size(); i++) {
                    if(i % 2 == 1) {
                        Reservation reservation = new Reservation();

                        reservation.setReservationTime(LocalDate.now().plusDays(4 * i));
                        reservation.setUser(userList.get(i));

                        reservationRepository.save(reservation);
                    }
                }
            /* FINISH Making User dummy data */


        /* FINISH For test dummy data save */


            }
        };
    }

}
