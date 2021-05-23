package com.msjo.move_in_reservation.service;

import com.msjo.move_in_reservation.domain.Reservation;
import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.repository.ApartmentRepository;
import com.msjo.move_in_reservation.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Reservation findByPhoneNumber(String phoneNumber) {

        logger.info("===== Params");
        logger.info("===== phoneNumber : " + phoneNumber);

        Reservation reservation = reservationRepository.findByUserId(phoneNumber).orElse(new Reservation());
        if(reservation.getId() != 0)
            reservation.getUser().setPassword("");

        logger.info("======= Reservation : " + reservation.toString());

        return reservation;
    }

    @Transactional
    public List<Reservation> findReservations(String dong) {

        logger.info("===== Params");
        logger.info("===== dong : " + dong);

        List<Reservation> reservations = reservationRepository.findReservationsByUserApartmentDongOrderByReservationTimeAsc(dong);

        for(Reservation reservation : reservations) {
            //필요없는 정보 초기화
            reservation.setId(0);
        }

        logger.info("======= Reservation's size : " + reservations.size());
        logger.info("======= Return value : " + reservations.toString());

        return reservations;
    }

    @Transactional
    public String cancelReservation(String phoneNumber) {

        String result = "";

        logger.info("===== Params");
        logger.info("===== phoneNumber : " + phoneNumber);

        Reservation reservation = reservationRepository.findByUserId(phoneNumber).orElse(new Reservation());

        logger.info("====== find Reservation : " + reservation);

        if (reservation.getUser() == null) {
            result = "FAILURE";
        } else {
            reservationRepository.delete(reservation);
            result = "SUCCESS";
        }

        logger.info("======= Return value : " + result);

        return result;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String createReservation(User user, LocalDate reservationTime) {

        String result = "";

        logger.info("===== Params");
        logger.info("===== user : " + user);
        logger.info("===== reservationTime : " + reservationTime);

        Reservation reservation = reservationRepository.findByUserId(user.getId()).orElse(new Reservation());

        logger.info("====== find Reservation : " + reservation);

        //날짜와 동이 일치하는 예약건
        Reservation duplicateReservation = reservationRepository.findReservationByUserApartmentDongAndReservationTime(
                user.getApartment().getDong(), reservationTime).orElse(new Reservation());

        logger.info("====== duplicate time and dong Reservation : " + duplicateReservation);


        /* 예약 일시 Validation */
        //기존 예약 정보가 있을 시,
        if (reservation.getId() != 0 ||
                //오늘 이전의 날에 대한 예약인지,
                !reservationTime.isAfter(LocalDate.now()) ||
                //60일 이후의 예약인지
                !reservationTime.isBefore(LocalDate.now().plusDays(60))) {

            logger.info("====== already exist user's reservation or invalid time");

            result = "FAILURE";
        }
        /* 예약 조건(세대) Validation */
        else if (duplicateReservation.getId() != 0) {

            logger.info("====== already exist duplicate reservation");

            result = "FAILURE";
        }
        else {

            result = "SUCCESS";

            Reservation saveReservation = new Reservation(user, reservationTime);

            logger.info("====== created reservation : " + saveReservation);

            reservationRepository.save(saveReservation);
        }

        logger.info("======= Return value : " + result);

        return result;
    }
}
