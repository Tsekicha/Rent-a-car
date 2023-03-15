package com.example.rentacar.convertor;

import com.example.rentacar.dto.ReservationRequest;
import com.example.rentacar.dto.ReservationResponse;
import com.example.rentacar.entity.Reservation;
import com.example.rentacar.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
@RequiredArgsConstructor
public class ReservationConvertor {

    private final ReservationRepository reservationRepository;

    public static Reservation toReservationRequest(ReservationRequest reservationRequest){


        return Reservation.builder()
                .car(reservationRequest.getCar())
                .user(reservationRequest.getUser())
                .startingRentDate(Instant.now())
                .expirationRentDate(reservationRequest.getExpirationRentDate())
                .build();
    }

    public ReservationResponse toReservationResponse(Reservation reservationResponse){

        return  ReservationResponse.builder()
                .car(reservationResponse.getCar())
                .user(reservationResponse.getUser())
                .startingRentDate(reservationResponse.getStartingRentDate())
                .expirationRentDate(reservationResponse.getExpirationRentDate())
                .finalPrice(reservationResponse.getFinalPrice())
                .build();
    }


}
