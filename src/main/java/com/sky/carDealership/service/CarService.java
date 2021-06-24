package com.sky.carDealership.service;

import com.sky.carDealership.model.Car;
import com.sky.carDealership.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsMatchingBrand(String brand) {
        return carRepository.findByAvailableTrueAndBrandIgnoreCase(brand);
    }

    public List<Car> sortByYear(List<Car> carList) {
        carList.sort((car1, car2) -> Integer.compare(car2.getYear(), car1.getYear()));
        return carList;
    }

    public List<Car> sortByPrice(List<Car> carList) {
        carList.sort(Comparator.comparingInt(Car::getPrice));
        return carList;
    }

    public List<Car> sortByMileage(List<Car> carList) {
        carList.sort(Comparator.comparingInt(Car::getMileage));
        return carList;
    }

    public List<Car> sortByBrand(List<Car> carList) {
        carList.sort(Comparator.comparing(Car::getBrand));
        return carList;
    }

    public List<Car> getAllAvailableCars() {
        return carRepository.findByAvailableTrue();
    }
}
