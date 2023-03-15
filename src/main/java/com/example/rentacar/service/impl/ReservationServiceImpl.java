package com.example.rentacar.service.impl;


import com.example.rentacar.convertor.ReservationConvertor;
import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import com.example.rentacar.entity.User;
import com.example.rentacar.repository.ReservationRepository;
import com.example.rentacar.service.CarService;
import com.example.rentacar.service.ReservationService;
import com.example.rentacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationConvertor reservationConvertor;
    private final ReservationRepository reservationRepository;
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public ReservationServiceImpl(ReservationConvertor reservationConvertor, ReservationRepository reservationRepository, CarService carService, UserService userService) {
        this.reservationConvertor = reservationConvertor;
        this.reservationRepository = reservationRepository;
        this.carService = carService;
        this.userService = userService;
    }

    @Override
    public ReservationResponse saveReservation(ReservationRequest reservationRequest) {

        Reservation reservationToBeSaved = ReservationConvertor.toReservationRequest(reservationRequest);
        User user = userService.findById(reservationRequest.getUser().getId());
        Car car = carService.findCarById(reservationRequest.getCar().getId());
        reservationToBeSaved.setCar(car);
        reservationToBeSaved.setUser(user);
        reservationToBeSaved.setFinalPrice(reservationPrice(reservationToBeSaved.getStartingRentDate(),reservationToBeSaved.getExpirationRentDate(),reservationToBeSaved.getCar()));

        return reservationConvertor.toReservationResponse(reservationRepository.save(reservationToBeSaved));
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation findReservationByUser(User user) {
        return null;
    }

    @Override
    public Reservation findReservationByCar(Car car) {

        return null;
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public BigDecimal reservationPrice(Instant firstDate, Instant lastDate, Car car) {

        BigDecimal dailyPrice = car.getDailyPrice();

        Duration duration = Duration.between(firstDate, lastDate);
        long days = duration.toDays();
        int daysAsInt = Math.toIntExact(days);

        return dailyPrice.multiply(BigDecimal.valueOf(daysAsInt));
    }
}
