package com.example.rentacar.repository;

import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Id> {
    void deleteById(Long id);
    Reservation findById(Long id);

}
