package com.msjo.move_in_reservation.controller;

import com.msjo.move_in_reservation.domain.User;
import com.msjo.move_in_reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public ResponseEntity<User> postLogin(@RequestBody User user, HttpSession session) {

        User tryUser = user;
        User loginUser = userService.findByPhoneNumber(user.getId());
        User sessionUser = (User) session.getAttribute("loginUser");

                                        //패스워드 불일치
        if(loginUser.getId() == null ||
                (!passwordEncoder.matches(tryUser.getPassword(), loginUser.getPassword()) && loginUser.getId().equals(tryUser.getId()))) {

            logger.info("LOGIN FAIL");
            logger.info("TRY USER INFO " + user.toString());

            return ResponseEntity.badRequest().build();
        }

        if(sessionUser != null)
            session.removeAttribute("loginUser");

        tryUser.setPassword("");

        if(loginUser.getId() != null) {
            loginUser.setPassword("");
            session.setAttribute("loginUser", loginUser);
        }

            logger.info("LOGIN SUCCESS");
            logger.info("SAVED USER INFO " + loginUser.toString());

            return ResponseEntity.ok(loginUser);

    }

    @PostMapping("logout")
    public ResponseEntity<Void> postLogout(HttpSession session) {

        session.removeAttribute("loginUser");

        return ResponseEntity.ok().build();
    }

}
