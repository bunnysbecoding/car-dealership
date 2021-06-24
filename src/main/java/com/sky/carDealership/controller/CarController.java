package com.sky.carDealership.controller;

import com.sky.carDealership.enums.OrderEnum;
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

    private static final String CAR_LIST_EMPTY_MESSAGE = "Your car list is empty";

    @ResponseBody
    @GetMapping("/cars/{id}/")
    public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
        Optional<Car> car = carService.getCarById(id);

        if (car.isPresent()) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find car matching id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/cars")
    public ResponseEntity<?> getOrderedCars(@RequestParam(value = "brand") Optional<String> brand, @RequestParam(value = "order") Optional<String> order) {

        List<Car> carList;

        if (brand.isEmpty()) {
            carList = carService.getAllAvailableCars();
        } else if (brand.get().isBlank()) {
            return new ResponseEntity<>("Can't filter by empty string", HttpStatus.BAD_REQUEST);
        } else {
            carList = carService.getCarsMatchingBrand(brand.get());
        }

        if(order.isPresent()) {
            switch (OrderEnum.orderEnumValueOf(order.get())){
                case YEAR : carService.sortByYear(carList); break;
                case PRICE : carService.sortByPrice(carList); break;
                case MILEAGE: carService.sortByMileage(carList); break;
                case BRAND : carService.sortByBrand(carList); break;
                default : return new ResponseEntity<>(invalidOrderMessage(order.get()), HttpStatus.BAD_REQUEST);
            }
        }

        if (carList.isEmpty()){
            return new ResponseEntity<>(CAR_LIST_EMPTY_MESSAGE, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    private String invalidOrderMessage(String order) {
        StringBuilder orderMessage = new StringBuilder(String.format("This order is not valid - \"%s\"\n", order));
        orderMessage.append(String.format("The following are valid order types: %s", OrderEnum.validOrdersStr()));
        return orderMessage.toString();
    }

}
