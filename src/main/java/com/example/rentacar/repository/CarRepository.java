package com.example.rentacar.repository;

import com.example.rentacar.entity.Car;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Id> {
    Car findById(Long id);

    void deleteById(Long id);
}
