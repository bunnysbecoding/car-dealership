package com.sky.carDealership.repository;

import com.sky.carDealership.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Optional<Car> findById(Long id);
    List<Car> findByAvailableTrueAndBrandIgnoreCase(String brand);
    List<Car> findAllByAvailableTrueOrderedByYearDesc();
    List<Car> findAllByAvailableTrueOrderedByBrand();
    List<Car> findAllByAvailableTrueOrderedByMileageAsc();
    List<Car> findAllByAvailableTrueOrderedByPriceAsc();
    List<Car> findAllByAvailableTrue();
}
