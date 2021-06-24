package com.sky.carDealership.repository;

import com.sky.carDealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    List<Car> findByAvailableTrueAndBrandIgnoreCase(String brand);
    List<Car> findByAvailableTrue();
}
