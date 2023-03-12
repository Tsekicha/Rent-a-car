package com.example.rentacar.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponse {

    private String brand;
    private String model;
    private int seats;
    private BigDecimal dailyPrice;
}
