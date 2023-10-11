package com.example.Swigatto.exceptions;

public class CustomerNotFoundException extends  RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
