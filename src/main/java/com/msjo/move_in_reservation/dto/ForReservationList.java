package com.msjo.move_in_reservation.dto;

import com.msjo.move_in_reservation.domain.Apartment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ForReservationList {

    private LocalDateTime reservationTime;

    private Apartment apartment;
}
