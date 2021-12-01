package com.alten.bookingservice.exception.handler;

import com.alten.bookingservice.exception.AdvanceReservationException;
import com.alten.bookingservice.exception.ServiceCantGetDataException;
import com.alten.bookingservice.exception.NotFoundException;
import com.alten.bookingservice.exception.OutOfRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorBody exceptionHandler(MethodArgumentNotValidException validationException) {
        var messages = validationException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ErrorBody(HttpStatus.BAD_REQUEST, messages);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OutOfRangeException.class)
    public ErrorBody exceptionHandler(OutOfRangeException exception) {
        return new ErrorBody(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AdvanceReservationException.class)
    public ErrorBody exceptionHandler(AdvanceReservationException exception) {
        return new ErrorBody(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorBody exceptionHandler(NotFoundException exception) {
        return new ErrorBody(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceCantGetDataException.class)
    public ErrorBody exceptionHandler(ServiceCantGetDataException exception) {
        return new ErrorBody(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage());
    }
}
