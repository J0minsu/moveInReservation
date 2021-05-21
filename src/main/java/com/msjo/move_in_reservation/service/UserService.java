package com.msjo.move_in_reservation.service;

import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public User findByPhoneNumber(String phoneNumber) {

        User user = userRepository.findById(phoneNumber).orElse(new User());

        return user;
    }

    @Transactional
    public String register(User user) {

        String result = "";
        User emptyUser = new User();

        User validateExistUser = userRepository.findById(user.getId()).orElse(emptyUser);

        User validateDuplicateAddressUser = userRepository.findUserByApartmentDongAndAndApartmentHo(
                user.getApartment().getDong(), user.getApartment().getHo()).orElse(emptyUser);

           //이미 가입자인지,
        if(validateExistUser.getId() == null ||
           //중복된 세대에 관한 회원가입인지
           validateDuplicateAddressUser.getId() == null) result = "FAILURE";
        else {

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            result = "SUCCESS";
        }

        return result;
    }
}
