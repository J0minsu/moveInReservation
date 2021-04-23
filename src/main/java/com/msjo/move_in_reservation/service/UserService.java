package com.msjo.move_in_reservation.service;

import com.msjo.move_in_reservation.domain.User;

public interface UserService {
    User findByPhoneNumber(String phoneNumber);
}
