package com.example.rentacar.dto;

import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.User;
import lombok.*;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationRequest {

    private User user;
    private Car car;
    private Instant expirationRentDate;
}
