package com.msjo.move_in_reservation.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate reservationTime;

    @Column
    @NotNull
    private LocalDate bookingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @NotNull
    private User user;

    public Reservation(User user, LocalDate reservationTime) {
        this.user = user;
        this.reservationTime = reservationTime;
    }

    @PrePersist
    public void setTime() {
        this.bookingTime = LocalDate.now();
    }
}
