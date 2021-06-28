package com.sky.carDealership.controller;

import com.sky.carDealership.dto.BookingDto;
import com.sky.carDealership.enums.RestCustomExceptionEnum;
import com.sky.carDealership.model.Booking;
import com.sky.carDealership.model.Car;
import com.sky.carDealership.model.User;
import com.sky.carDealership.repository.CarRepository;
import com.sky.carDealership.repository.UserRepository;
import com.sky.carDealership.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookingController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingService bookingService;


    @ResponseBody
    @PostMapping("/bookings/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingDto booking) {
        Optional<Car> car = carRepository.findById(booking.getCarId());
        Optional<User> user = userRepository.findById(booking.getUserId());

        if (car.isEmpty() || user.isEmpty()) {
            return RestCustomExceptionEnum.INVALID_ID_EXCEPTION.customResponse();
        } else if (!car.get().isAvailable()) {
            return RestCustomExceptionEnum.CAR_UNAVAILABLE_EXCEPTION.customResponse();
        }

        Optional<Booking> createdBooking = bookingService.createBooking(car.get(), user.get());

        if(createdBooking.isPresent()) {
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } else {
            return RestCustomExceptionEnum.BOOKING_UNSUCCESSFUL_EXCEPTION.customResponse();
        }
    }

    @ResponseBody
    @PutMapping("/bookings/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") Long id) {
        Optional<Booking> booking = bookingService.getBooking(id);

        if (booking.isEmpty()) {
            return RestCustomExceptionEnum.BOOKING_NOT_FOUND_EXCEPTION.customResponse();
        } else {
            Optional<Booking> cancelledBooking = bookingService.cancelBooking(booking.get());

            return cancelledBooking.isEmpty()
                    ? RestCustomExceptionEnum.BOOKING_NOT_FOUND_EXCEPTION.customResponse()
                    : new ResponseEntity<>(booking.get(),HttpStatus.ACCEPTED);
        }
    }

    @ResponseBody
    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBooking(@PathVariable("id") Long id) {
        Optional<Booking> booking = bookingService.getBooking(id);

        return booking.isEmpty()
            ? RestCustomExceptionEnum.BOOKING_NOT_FOUND_EXCEPTION.customResponse()
            : new ResponseEntity<>(booking.get(),HttpStatus.ACCEPTED);
    }
}
