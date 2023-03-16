package com.example.rentacar.service;


import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import com.example.rentacar.entity.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


public interface ReservationService {
    ReservationResponse saveReservation(ReservationRequest reservationRequest);
    Reservation findReservationById(Long id);
    void deleteReservation(Long id);

    BigDecimal reservationPrice(Instant firstDate, Instant lastDate, Car car);
    List<Reservation> getReservationsByUser(Long userId);
    List<Reservation> getReservationByCar(Long carID);
    List<Reservation> getReservationByPeriod(String period);
    List<Reservation> findReservationsByPeriod(Instant start, Instant end);
    Reservation updateReservationUser(Long reservationId,Long userId);
    Reservation updateReservationDate(Long reservationId, Reservation reservation);



}
