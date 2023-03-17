package com.example.rentacar.service.impl;


import com.example.rentacar.convertor.ReservationConvertor;
import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import com.example.rentacar.entity.User;
import com.example.rentacar.exception.ResourceNotFoundException;
import com.example.rentacar.repository.CarRepository;
import com.example.rentacar.repository.ReservationRepository;
import com.example.rentacar.repository.UserRepository;
import com.example.rentacar.service.CarService;
import com.example.rentacar.service.ReservationService;
import com.example.rentacar.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationConvertor reservationConvertor;
    private final ReservationRepository reservationRepository;
    private final CarService carService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Autowired
    public ReservationServiceImpl(ReservationConvertor reservationConvertor, ReservationRepository reservationRepository, CarService carService, UserService userService, UserRepository userRepository, CarRepository carRepository) {
        this.reservationConvertor = reservationConvertor;
        this.reservationRepository = reservationRepository;
        this.carService = carService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
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
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public List<Reservation> getReservationByCar(Long carID) {
        return reservationRepository.findByCarId(carID);
    }

    @Override
    public Reservation updateReservationUser(Long reservationId, Long userId) {

        Reservation reservation = reservationRepository.findById(reservationId);
        User user = userRepository.findById(userId);
        reservation.setUser(user);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservationDate(Long reservationId, Reservation reservation) {

        Optional<Reservation> optionalReservation = Optional.ofNullable(reservationRepository.findById(reservationId));
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            existingReservation.setExpirationRentDate(reservation.getExpirationRentDate());
            return reservationRepository.save(existingReservation);
        } else {
            throw new ResourceNotFoundException("Reservation not found with ID " + reservationId);
        }
    }
    public List<Reservation> getReservationsByPeriod(Instant startDate, Instant endDate){
        return reservationRepository.findReservationsForPeriod(startDate, endDate);
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
