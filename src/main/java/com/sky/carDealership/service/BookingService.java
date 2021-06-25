package com.sky.carDealership.service;

import com.sky.carDealership.model.Booking;
import com.sky.carDealership.model.Car;
import com.sky.carDealership.model.User;
import com.sky.carDealership.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Optional<Booking> createBooking(Car car, User user) {
        car.setAvailable(false);

        return Optional.of(bookingRepository.save(new Booking(car, user)));
    }

    public Optional<Booking> cancelBooking(Booking booking) {
        if (!booking.isActive()) {
            return Optional.empty();
        }

        booking.setActive(false);
        booking.getCar().setAvailable(true);

        return Optional.of(bookingRepository.save(booking));
    }

    public Optional<Booking> getBooking(Long id) {
        return bookingRepository.findById(id);
    }
}
