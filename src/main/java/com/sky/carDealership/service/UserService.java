package com.sky.carDealership.service;

import com.sky.carDealership.model.User;
import com.sky.carDealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> createUser(User user) {
        User savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }


}
