package com.msjo.move_in_reservation.dto;

import com.msjo.move_in_reservation.domain.Apartment;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ForReservationList {

    private LocalDate reservationTime;

    private Apartment apartment;
}
