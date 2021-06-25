package com.sky.carDealership.controller;

import com.sky.carDealership.dto.BookingDto;
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
    @PostMapping("/booking/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingDto booking) {
        Optional<Car> car = carRepository.findById(booking.getCarId());
        Optional<User> user = userRepository.findById(booking.getUserId());

        if (car.isEmpty() || user.isEmpty()) {
            return new ResponseEntity<>("The car and the user need be valid IDs", HttpStatus.BAD_REQUEST);
        } else if (!car.get().isAvailable()) {
            return new ResponseEntity<>("The car you are trying to book is unavailable", HttpStatus.BAD_REQUEST);
        }

        Optional<Booking> createdBooking = bookingService.createBooking(car.get(), user.get());

        if(createdBooking.isPresent()) {
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Booking unsuccessful", HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PutMapping("/booking/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") Long id) {
        Optional<Booking> booking = bookingService.getBooking(id);

        if (booking.isEmpty()) {
            return new ResponseEntity<>("Booking not found",HttpStatus.BAD_REQUEST);
        } else {
            Optional<Booking> cancelledBooking = bookingService.cancelBooking(booking.get());

            return cancelledBooking.isEmpty()
                    ? new ResponseEntity<>("Booking not found",HttpStatus.BAD_REQUEST)
                    : new ResponseEntity<>(booking.get(),HttpStatus.ACCEPTED);
        }
    }

    @ResponseBody
    @GetMapping("/booking/{id}")
    public ResponseEntity<?> getBooking(@PathVariable("id") Long id) {
        Optional<Booking> booking = bookingService.getBooking(id);

        return booking.isEmpty()
            ? new ResponseEntity<>("Booking not found",HttpStatus.BAD_REQUEST)
            : new ResponseEntity<>(booking.get(),HttpStatus.ACCEPTED);
    }
}
