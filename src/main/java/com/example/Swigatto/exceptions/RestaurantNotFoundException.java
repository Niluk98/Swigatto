package com.example.Swigatto.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public  RestaurantNotFoundException(String message){
        super(message);
    }
}
