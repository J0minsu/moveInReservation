package com.msjo.move_in_reservation.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table
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

    @ManyToOne
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;

}
