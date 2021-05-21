package com.msjo.move_in_reservation.service;

import com.msjo.move_in_reservation.repository.ApartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    ApartmentRepository apartmentRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public List<String> findDongList() {

        logger.info("========== START SERVICE LOGIC");

        List<String> dongList = new ArrayList<>();

        dongList = apartmentRepository.findDistinctDong();

        logger.info("Return value's size." + dongList.size());

        logger.info("========== FINISH SERVICE LOGIC");

        return dongList;
    }


}
