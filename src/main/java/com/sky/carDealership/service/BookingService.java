package com.sky.carDealership.service;

import com.sky.carDealership.model.Booking;
import com.sky.carDealership.model.Car;
import com.sky.carDealership.model.User;
import com.sky.carDealership.repository.BookingRepository;
import com.sky.carDealership.repository.CarRepository;
import com.sky.carDealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Booking> createBooking(Car car, User user) {
        car.setAvailable(false);

        return Optional.of(bookingRepository.save(new Booking(car, user)));
    }
}
