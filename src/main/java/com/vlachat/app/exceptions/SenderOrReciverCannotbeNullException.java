package com.vlachat.app.exceptions;

public class SenderOrReciverCannotbeNullException extends RuntimeException{// Bunu yaziriq ki frontend yazanin yadindan cixar field-leri doldurmaz  
    public SenderOrReciverCannotbeNullException(String message){
        super(message);
    }
}// M-5