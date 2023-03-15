package com.example.rentacar.controller;

import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;

import com.example.rentacar.entity.Reservation;
import com.example.rentacar.repository.ReservationRepository;
import com.example.rentacar.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @PostMapping(path = "/saveReservation")
    ResponseEntity<ReservationResponse> saveReservation(@RequestBody ReservationRequest reservationRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService.saveReservation(reservationRequest));
    }
}
