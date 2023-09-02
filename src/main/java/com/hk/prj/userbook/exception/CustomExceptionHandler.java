package com.hk.prj.userbook.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleAllException(UserNotFoundException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.toString(), e.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidMethodException.class)
    public final ResponseEntity<Object> handleInvalidMethodException(InvalidMethodException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorCode = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errorCode, request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
