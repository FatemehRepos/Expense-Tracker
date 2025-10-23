package com.fathi.expense.tracker.component.exception;

public class SystemException extends RuntimeException {

    public SystemException() {
        super("errors.system.error");
    }

}
