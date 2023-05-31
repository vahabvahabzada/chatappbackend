package com.vlachat.app.exceptions;

public class CannotBeEmptyException extends RuntimeException{
    public CannotBeEmptyException(String message){
        super(message);
    }
}
