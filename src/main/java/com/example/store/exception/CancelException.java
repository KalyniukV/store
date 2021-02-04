package com.example.store.exception;

public class CancelException extends RuntimeException {

    public CancelException() {
        super("You cancel operation");
    }
}
