package com.example.rentacar.dto;

import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {

    private User user;
    private Car car;
    private Instant startingRentDate = Instant.now();
    private Instant expirationRentDate;
    private BigDecimal finalPrice;

}
