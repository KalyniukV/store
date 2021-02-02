package com.example.store.exception;

public class CancelException extends Exception {

    public CancelException() {
        super("You cancel operation");
    }
}
