package com.msjo.move_in_reservation.domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reservation {


    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    @NotNull
    private LocalDateTime reservationTime;

    @ManyToOne
    @JoinColumn(name = "uesrId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;
}
