package com.example.rentacar.convertor;

import com.example.rentacar.dto.CarRequest;
import com.example.rentacar.dto.CarResponse;
import com.example.rentacar.dto.UserRequest;
import com.example.rentacar.dto.UserResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CarConvertor {

    public static Car toCarRequest(CarRequest carRequest){

        return Car.builder()
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .seat(carRequest.getSeats())
                .dailyPrice(carRequest.getDailyPrice())
                .build();
    }

    public CarResponse toCarResponse(Car carResponse){

        return CarResponse.builder()
                .brand(carResponse.getBrand())
                .model(carResponse.getModel())
                .seats(carResponse.getSeat())
                .dailyPrice(carResponse.getDailyPrice())
                .build();
    }
}
