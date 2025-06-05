package com.solo.gilded.rose.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","Invalid input", "exception", exception.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An unexpected error occurred", "exception",ex.getMessage()));
    }
}
