package com.example.rentacar.service;

import com.example.rentacar.dto.CarRequest;
import com.example.rentacar.dto.CarResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.User;
import org.springframework.stereotype.Service;

public interface CarService {
    CarResponse saveCar(CarRequest carRequest);
    Car updateCar(Car car, Long id);
    Car findCarById(Long id);
    void deleteCar(Long id);

}
