package com.fathi.expense.tracker.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super(String.format("error.authentication.failed", username));
    }

}
