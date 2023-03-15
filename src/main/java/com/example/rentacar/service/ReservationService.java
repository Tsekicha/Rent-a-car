package com.example.rentacar.service;


import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import com.example.rentacar.entity.User;

import java.math.BigDecimal;
import java.time.Instant;


public interface ReservationService {
    ReservationResponse saveReservation(ReservationRequest reservationRequest);
    Reservation findReservationById(Long id);
    Reservation findReservationByUser(User user);
    Reservation findReservationByCar(Car car);
   // Reservation findReservationByPeriod();
    void deleteReservation(Long id);
   // Reservation updateReservationCar();
   // Reservation updateReservationDate();
    BigDecimal reservationPrice(Instant firstDate, Instant lastDate, Car car);


}
