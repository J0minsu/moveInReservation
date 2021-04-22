package com.msjo.move_in_reservation.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Id;

public class User {

    @Id
    @Column(length = 20)
    @NotNull
    private String phoneNumber;

    @Column(length = 60)
    @NotNull
    private String password;

    @Column
    private String name;

}
