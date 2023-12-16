package com.itana.crud_csvbackend.shared.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerHandlerException {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageException resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return new ErrorMessageException(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageException handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String messageException = errors.values().iterator().next();

        return new ErrorMessageException(
                HttpStatus.BAD_REQUEST.value(),
                messageException,
                request.getDescription(false),
                LocalDateTime.now()
        );
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageException globalExceptionHandler(Exception exception, WebRequest request) {
        return new ErrorMessageException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }
}
