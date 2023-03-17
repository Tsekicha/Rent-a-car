package com.example.rentacar.controller;

import com.example.rentacar.dto.CarRequest;
import com.example.rentacar.dto.CarResponse;
import com.example.rentacar.entity.Car;
import com.example.rentacar.entity.User;
import com.example.rentacar.repository.CarRepository;
import com.example.rentacar.repository.UserRepository;
import com.example.rentacar.service.CarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    ResponseEntity<CarResponse> saveCar(@RequestBody CarRequest carRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.saveCar(carRequest));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(carRepository.findById(id));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    ResponseEntity<String> deleteCar(@PathVariable("id") Long id){
        carRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .body(String.format("%d deleted", id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(car,id);
        return ResponseEntity.ok(updatedCar);
    }
}
