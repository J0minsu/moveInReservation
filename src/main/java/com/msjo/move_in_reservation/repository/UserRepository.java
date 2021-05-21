package com.msjo.move_in_reservation.repository;

import com.msjo.move_in_reservation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByApartmentDongAndAndApartmentHo(String dong, String ho);
}
