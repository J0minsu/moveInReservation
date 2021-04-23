package com.msjo.move_in_reservation.controller;

import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> postLogin(@RequestBody User user, HttpSession session) {

        User loginUser = userService.findByPhoneNumber(user.getPhoneNumber());
        loginUser.setPassword("");

        if(loginUser.getPhoneNumber() != null) {
            session.setAttribute("loginUser", loginUser);

            return ResponseEntity.ok().body("SUCCESS");
        }

        return ResponseEntity.badRequest().body("FAILURE");

    }

}
