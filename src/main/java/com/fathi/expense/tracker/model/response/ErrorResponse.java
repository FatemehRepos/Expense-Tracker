package com.fathi.expense.tracker.model.response;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int code;
    private final String subject;
    private final String message;

    private ErrorResponse(int code, String subject, String message) {
        this.code = code;
        this.subject = subject;
        this.message = message;
    }

    public static ErrorResponse error(int code, String subject, String message) {
        return new ErrorResponse(code, subject, message);
    }

}
