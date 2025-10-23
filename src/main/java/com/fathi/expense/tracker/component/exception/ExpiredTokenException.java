package com.fathi.expense.tracker.component.exception;

import jakarta.servlet.http.HttpServletResponse;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException(String message) {
        super(message);
        int errorCode = HttpServletResponse.SC_UNAUTHORIZED;
    }

}
