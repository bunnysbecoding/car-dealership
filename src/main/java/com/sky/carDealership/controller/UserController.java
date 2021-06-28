package com.sky.carDealership.controller;

import com.sky.carDealership.enums.RestCustomExceptionEnum;
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

        if (isInvalidString(user.getName()) || isInvalidString(user.getSurname())) {
            badRequest = true;
            errorMessage.append("The user name and surname need to be non-numeric characters; ");
        } else if (isInvalidEmail(user.getEmail())) {
            badRequest = true;
            errorMessage.append("The user email need to be a valid address.");
        }

        if (badRequest) {
            return RestCustomExceptionEnum.INVALID_USER_PARAMETERS_EXCEPTION.customResponse(errorMessage.toString());//new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Optional<User> createdUser = userService.createUser(user);

        if (createdUser.isEmpty()){
            return RestCustomExceptionEnum.DUPLICATE_EMAIL_EXCEPTION.customResponse("User creation unsuccessful - the email address '%s' is not unique", user.getEmail());
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    private boolean isInvalidString(String string) {
        return string == null || !string.matches("[a-zA-Z]+");
    }

    private boolean isInvalidEmail(String email) {
        return email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

}
