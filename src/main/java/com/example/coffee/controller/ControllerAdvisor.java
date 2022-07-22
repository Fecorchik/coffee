package com.example.coffee.controller;

import com.example.coffee.exception.CoffeeMakerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CoffeeMakerException.class)
    public ResponseEntity<String> handleCoffeeMakerException(CoffeeMakerException ex, WebRequest request) {
        return new ResponseEntity<>(String.format(ex.getMessage(), ex.getArgs()), ex.getStatus());
    }

}