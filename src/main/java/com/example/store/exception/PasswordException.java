package com.example.store.exception;

public class PasswordException extends Exception {

    public PasswordException(String password) {
        super("Wrong password: " + password);
    }
}
