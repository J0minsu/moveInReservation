package com.msjo.move_in_reservation.service.impl;

import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.repository.UserRepository;
import com.msjo.move_in_reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByPhoneNumber(String phoneNumber) {

        User user = userRepository.findById(phoneNumber).orElse(new User());

        return user;
    }
}
