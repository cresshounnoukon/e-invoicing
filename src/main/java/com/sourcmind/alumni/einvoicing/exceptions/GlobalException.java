package com.sourcmind.alumni.einvoicing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    public Error handleNotFoundException(NotFoundException exception) {
        return exception.getError();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {AlreadyExistException.class})
    public Error handleAlreadyExistException(AlreadyExistException exception) {
        return exception.getError();
    }
}
