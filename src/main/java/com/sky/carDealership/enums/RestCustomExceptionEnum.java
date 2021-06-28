package com.sky.carDealership.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum RestCustomExceptionEnum {
    CAR_NOT_FOUND_EXCEPTION("Car not found", "CAR_REPO_ERR001", HttpStatus.NOT_FOUND),
    BOOKING_NOT_FOUND_EXCEPTION("Booking not found", "CAR_REPO_ERR002", HttpStatus.NOT_FOUND),
    CAR_UNAVAILABLE_EXCEPTION("Car is unavailable", "CAR_REPO_ERR003", HttpStatus.BAD_REQUEST),
    EMPTY_REQUEST_PARAMETER_EXCEPTION("Cannot filter by empty string", "CAR_REPO_ERR004", HttpStatus.BAD_REQUEST),
    INVALID_ORDER_REQUEST_EXCEPTION("This order is not valid", "CAR_REPO_ERR005", HttpStatus.BAD_REQUEST),
    INVALID_USER_PARAMETERS_EXCEPTION("The username and email need to be valid.", "CAR_REPO_ERR006", HttpStatus.BAD_REQUEST),
    DUPLICATE_EMAIL_EXCEPTION("Email needs to be unique", "CAR_REPO_ERR007", HttpStatus.BAD_REQUEST),
    INVALID_ID_EXCEPTION("The car and the user need be valid IDs", "CAR_REPO_ERR008", HttpStatus.BAD_REQUEST),
    BOOKING_UNSUCCESSFUL_EXCEPTION("Booking unsuccessful","CAR_REPO_ERR009",HttpStatus.BAD_REQUEST);

    private final HttpStatus status;
    private final String errorMessage;
    private final String customErrorCode;


    RestCustomExceptionEnum(String errorMessage, String customErrorCode, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.customErrorCode = customErrorCode;
        this.status = status;
    }

    @Override
    public String toString() {
        return  "{\n" +
                "    \"status\": \"" + status + "\",\n" +
                "    \"errorMessage\": \"" + errorMessage + "\",\n" +
                "    \"customErrorCode\": \"" + customErrorCode + "\"\n" +
                '}';
    }


    public String customToString(String format, Object... objects) {
        return  "{\n" +
                "    \"status\": \"" + status + "\",\n" +
                "    \"errorMessage\": \"" + String.format(format, objects) + "\",\n" +
                "    \"customErrorCode\": \"" + customErrorCode + "\"\n" +
                '}';

    }

    public ResponseEntity<String> customResponse() {
        return new ResponseEntity<>(toString(),this.status);
    }

    public ResponseEntity<String> customResponse(String format, Object... objects) {
        return new ResponseEntity<>(customToString(format, objects),this.status);
    }
}
