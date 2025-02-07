package com.gatepass.exceptions;

public class FailedToSavePassException extends RuntimeException{
    public FailedToSavePassException(String message){
        super(message);
    }
}
