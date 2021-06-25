package com.sky.carDealership.enums;

import org.springframework.http.HttpStatus;

public enum RestCustomExceptionEnum {
    CAR_NOT_FOUND_EXCEPTION("Car not found", "Error code", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_EXCEPTION("User not found", "Error code", HttpStatus.NOT_FOUND),
    BOOKING_NOT_FOUND_EXCEPTION("Booking not found", "Error code", HttpStatus.NOT_FOUND),
    CAR_UNAVAILABLE_EXCEPTION("Car is unavailable", "Error code", HttpStatus.BAD_REQUEST),
    EMPTY_REQUEST_PARAMETER_EXCEPTION("Cannot filter by empty string", "Error code", HttpStatus.BAD_REQUEST);


    private HttpStatus status;
    private String errorMessage;
    private String customErrorCode;


    RestCustomExceptionEnum(String errorMessage, String customErrorCode, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.customErrorCode = customErrorCode;
        this.status = status;
    }

    @Override
    public String toString() {
        return  "{" +
                "   status=" + status +
                "   , errorMessage='" + errorMessage + '\'' +
                "   , customErrorCode='" + customErrorCode + '\'' +
                '}';
    }


    public String customToString(String format, Object... objects) {
        return  "{" +
                "   status=" + status + ",\n" +
                "   " + String.format(format, objects) + ",\n" +
                "   customErrorCode='" + customErrorCode + ",\n" +
                '}';

    }
}
