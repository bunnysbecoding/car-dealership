package com.sky.carDealership.controller;

import com.sky.carDealership.enums.OrderEnum;
import com.sky.carDealership.model.Car;
import com.sky.carDealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class CarController {

    @Autowired
    private CarService service;

    @ResponseBody
    @GetMapping("/cars/{id}/")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
        Optional<Car> car = service.getCarById(id);

        if (car.isPresent()) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find car matching id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/cars")
    public ResponseEntity<?> getCarsMatchingBrand(@RequestParam("brand") String brand) {
        return new ResponseEntity<>(service.getCarsMatchingBrand(brand), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/cars")
    public ResponseEntity<?> getOrderedCars(@RequestParam("order") String order) {

        List<Car> carList;

        switch (OrderEnum.valueOf(order)){
            case YEAR : carList = service.getCarsOrderedByYear(); break;
            case PRICE : carList = service.getCarsOrderedByPrice(); break;
            case MILEAGE: carList = service.getCarsOrderedByMileage(); break;
            case BRAND : carList = service.getCarsOrderedByBrand(); break;
            default: carList = service.getAllAvailableCars(); break;

        }

        if (carList.isEmpty()){
            return new ResponseEntity<>("Your List is Empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getCarsOrderedByYear(), HttpStatus.OK);


    }

}
