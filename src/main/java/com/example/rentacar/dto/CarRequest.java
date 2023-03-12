package com.example.rentacar.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarRequest {

    private String brand;
    private String model;
    @Size(min = 2, message = "Car must have at least 2 seats!")
    private int seats;
    private BigDecimal dailyPrice;
}
