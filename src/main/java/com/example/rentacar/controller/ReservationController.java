package com.example.rentacar.controller;

import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;

import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.Reservation;
import com.example.rentacar.entity.User;
import com.example.rentacar.exception.ResourceNotFoundException;
import com.example.rentacar.repository.ReservationRepository;
import com.example.rentacar.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


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
    @GetMapping(path = "/{id}")
    ResponseEntity<Reservation> findById(@PathVariable("id") Long id){
        return  ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationRepository.findById(id));
    }
    @GetMapping(path = "/user/{id}")
    ResponseEntity<List<Reservation>> findReservationByUser(@PathVariable("id") Long id){
        return  ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationRepository.findByUserId(id));
    }
    @GetMapping(path = "/car/{id}")
    ResponseEntity<List<Reservation>> findReservationByCar(@PathVariable("id") Long id){
        return  ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationRepository.findByCarId(id));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    ResponseEntity<String> deleteReservation(@PathVariable("id") Long id){
        reservationRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .body(String.format("%d deleted", id));
    }
    @PutMapping("/update/{reservationId}/reservationByUser/{userId}")
    public ResponseEntity<Reservation> updateReservationOnUser(@PathVariable Long reservationId, @PathVariable Long userId) {
        Reservation updatedReservation = reservationService.updateReservationUser(reservationId, userId);
        return ResponseEntity.ok(updatedReservation);
    }

    @PutMapping("/update/reservationByDate/{reservationId}")
    public ResponseEntity<Reservation> updateReservationOnDate(@PathVariable Long reservationId, @RequestBody Reservation reservation) {
        try {
            Reservation updatedReservation = reservationService.updateReservationDate(reservationId, reservation);
            return ResponseEntity.ok(updatedReservation);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getPeriodReservation")
    public ResponseEntity<List<Reservation>> getReservationsForPeriod(
            @RequestParam(name = "start_date") Instant startDate,
            @RequestParam(name = "end_date") Instant endDate
    ) {
        List<Reservation> reservations = reservationService.findReservationsByPeriod(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }

}
