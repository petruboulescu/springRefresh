package com.petruboulescu.exception;

import com.petruboulescu.dto.ExceptionDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    //For fields annotated with @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handleMethodArgumentNotValid(MethodArgumentNotValidException exception) { //
         //Get the fields that failed validation, get their name, collect them to a list
        List<String> fieldNames = exception.getBindingResult().
                getFieldErrors().stream().map(FieldError::getField).toList();
        return new ExceptionDto(fieldNames, exception.getMessage());
    }

    //For exceptions raised by Hibernate
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ExceptionDto handleConstraintViolation(ConstraintViolationException exception) {
        //Get the fields that failed validation, get their name, collect them to a list
        List<String> invalidFields = exception.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath().toString())
                .toList();
        return new ExceptionDto(invalidFields, exception.getMessage());
    }

}
