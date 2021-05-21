package com.msjo.move_in_reservation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {

    @Id
    @Column(length = 20)
    @NotNull
    private String id;

    @Column(length = 60)
    @NotNull
    private String password;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartmentId")
    @NotNull
    private Apartment apartment;

}
