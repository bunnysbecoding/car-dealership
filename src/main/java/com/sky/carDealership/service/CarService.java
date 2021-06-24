package com.sky.carDealership.service;

import com.sky.carDealership.model.Car;
import com.sky.carDealership.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public Optional<Car> getCarById(Long id) {
        return repository.findById(id);
    }

    public List<Car> getCarsMatchingBrand(String brand) {
        return repository.findByAvailableTrueAndBrandIgnoreCase(brand);
    }

    public List<Car> getCarsOrderedByYear() {
        return repository.findAllByAvailableTrueOrderedByYearDesc();
    }

    public List<Car> getCarsOrderedByPrice() {
        return repository.findAllByAvailableTrueOrderedByPriceAsc();
    }

    public List<Car> getCarsOrderedByMileage() {
        return repository.findAllByAvailableTrueOrderedByMileageAsc();
    }

    public List<Car> getCarsOrderedByBrand() {
        return repository.findAllByAvailableTrueOrderedByBrand();
    }

    public List<Car> getAllAvailableCars() {
        return repository.findAllByAvailableTrue();
    }
}
