package com.msjo.move_in_reservation.domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@ToString
public class Apartment {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(length = 4)
    @NotNull
    private String dong;

    @Column(length = 4)
    @NotNull
    private String ho;
}
