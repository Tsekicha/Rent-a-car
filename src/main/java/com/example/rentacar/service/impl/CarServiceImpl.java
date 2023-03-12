package com.example.rentacar.service.impl;

import com.example.rentacar.convertor.CarConvertor;
import com.example.rentacar.dto.CarRequest;
import com.example.rentacar.dto.CarResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.User;
import com.example.rentacar.repository.CarRepository;
import com.example.rentacar.repository.UserRepository;
import com.example.rentacar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarConvertor carConvertor;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarConvertor carConvertor, CarRepository carRepository) {
        this.carConvertor = carConvertor;
        this.carRepository = carRepository;
    }
    @Override
    public CarResponse saveCar(CarRequest carRequest) {
        Car carToBeSaved = CarConvertor.toCarRequest(carRequest);
        return carConvertor.toCarResponse(carRepository.save(carToBeSaved));
    }
    @Override
    public Car updateCar(Car car, Long id) {

        Car existingCar = carRepository.findById(id);

        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setSeat(car.getSeat());
        existingCar.setDailyPrice(car.getDailyPrice());

        return carRepository.save(existingCar);
    }
    @Override
    public Car findCarById(Long id) {
        return carRepository.findById(id);
    }
    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
