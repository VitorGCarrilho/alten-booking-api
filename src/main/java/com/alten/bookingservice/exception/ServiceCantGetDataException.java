package com.alten.bookingservice.exception;

public class ServiceCantGetDataException extends RuntimeException {
    public ServiceCantGetDataException(String message) {
        super(message);
    }
}
