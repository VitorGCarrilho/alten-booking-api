package com.alten.bookingservice.exception.handler;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ErrorBody {
    private HttpStatus httpStatus;
    private List<String> messages;

    public ErrorBody(HttpStatus httpStatus, List<String> messages) {
        this.httpStatus = httpStatus;
        this.messages = messages;
    }

    public ErrorBody(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.messages = Collections.singletonList(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getMessages() {
        return messages;
    }
}
