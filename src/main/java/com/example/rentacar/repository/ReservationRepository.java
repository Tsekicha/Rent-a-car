package com.example.rentacar.repository;

import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Id> {
    
    Reservation findById(Long id);

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByCarId(Long carID);

    void deleteById(Long id);
    @Query(value = "SELECT * FROM reservations WHERE starting_rent_date <= :endDate AND final_rent_date >= :startDate", nativeQuery = true)
    List<Reservation> findReservationsForPeriod(Instant startDate, Instant endDate);
}
