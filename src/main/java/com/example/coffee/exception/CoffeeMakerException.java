package com.example.coffee.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoffeeMakerException extends RuntimeException{

    private HttpStatus status;
    private Object[] args;

    public CoffeeMakerException(String message, HttpStatus status, Object... args) {
        super(message);
        this.status = status;
        this.args = args;
    }

}
