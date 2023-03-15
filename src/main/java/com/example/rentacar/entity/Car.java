package com.example.rentacar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "brand",nullable = false)
    private String brand;
    @Column(name = "model",nullable = false)
    private String model;
    @Column(name = "seat",nullable = false)
    private int seat;
    @Column(name = "daily_price",nullable = false)
    private BigDecimal dailyPrice;


}
