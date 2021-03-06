package com.msjo.move_in_reservation.controller;

import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) {

        String result = userService.register(user);

        if(result.equals("FAILURE"))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();

    }

}
