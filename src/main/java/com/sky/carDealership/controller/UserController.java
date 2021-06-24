package com.sky.carDealership.controller;

import com.sky.carDealership.model.User;
import com.sky.carDealership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user) {
        StringBuilder errorMessage = new StringBuilder();
        boolean badRequest = false;

        if (!isValidString(user.getName()) || !isValidString(user.getSurname())) {
            badRequest = true;
            errorMessage.append("The user name and surname need to be non-numeric characters\n");
        } else if (!isValidEmail(user.getEmail())) {
            badRequest = true;
            errorMessage.append("The user email need to be valid");
        }

        if (badRequest) {
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Optional<User> createdUser = userService.createUser(user);

        if (createdUser.isEmpty()){
            return new ResponseEntity<>(String.format("User creation unsuccessful - the email address \"%s\" is not unique", user.getEmail()),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                user, HttpStatus.CREATED);

    }

    private boolean isValidString(String string) {
        if (string == null || !string.matches("[a-zA-Z]+")) {
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return false;
        }
        return true;
    }

}
