package com.sky.carDealership.controller;

import com.sky.carDealership.enums.OrderEnum;
import com.sky.carDealership.enums.RestCustomExceptionEnum;
import com.sky.carDealership.model.Car;
import com.sky.carDealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class CarController {

    @Autowired
    private CarService carService;

    @ResponseBody
    @GetMapping("/cars/{id}/")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
        Optional<Car> car = carService.getCarById(id);

        if (car.isPresent()) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return RestCustomExceptionEnum.CAR_NOT_FOUND_EXCEPTION.customResponse("Could not find car matching ID '%s'", id);
        }
    }

    @ResponseBody
    @GetMapping("/cars")
    public ResponseEntity<?> getOrderedCars(@RequestParam(value = "brand") Optional<String> brand, @RequestParam(value = "order") Optional<String> order) {

        List<Car> carList;

        if (brand.isEmpty()) {
            carList = carService.getAllAvailableCars();
        } else if (brand.get().isBlank()) {
            return RestCustomExceptionEnum.EMPTY_REQUEST_PARAMETER_EXCEPTION.customResponse();
        } else {
            carList = carService.getCarsMatchingBrand(brand.get());
        }

        if(order.isPresent()) {
            switch (OrderEnum.orderEnumValueOf(order.get())){
                case YEAR : carList = carService.sortByYear(carList); break;
                case PRICE : carList = carService.sortByPrice(carList); break;
                case MILEAGE: carList = carService.sortByMileage(carList); break;
                case BRAND : carList = carService.sortByBrand(carList); break;
                default : RestCustomExceptionEnum.INVALID_ORDER_REQUEST_EXCEPTION.customResponse(invalidOrderMessage(order.get()));
            }
        }

        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    private String invalidOrderMessage(String order) {
        StringBuilder orderMessage = new StringBuilder(String.format("This order is not valid - '%s'", order));
        orderMessage.append(String.format("The following are valid order types: %s", OrderEnum.validOrdersStr()));
        return orderMessage.toString();
    }

}
