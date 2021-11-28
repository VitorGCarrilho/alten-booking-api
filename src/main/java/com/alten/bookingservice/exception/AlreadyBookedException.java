package com.alten.bookingservice.exception;

public class AlreadyBookedException extends RuntimeException {

    public AlreadyBookedException(String message) {
        super(message);
    }
    public AlreadyBookedException(Throwable e) {
        super(e);
    }
}
