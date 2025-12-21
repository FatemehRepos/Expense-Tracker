package com.fathi.expense.tracker.component.exception;

import com.fathi.expense.tracker.model.response.ErrorResponse;
import com.fathi.expense.tracker.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    private final MessageSource messageSource;

    @org.springframework.web.bind.annotation.ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Response<ErrorResponse>> handleException(RecordNotFoundException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.error(
                HttpStatus.NOT_FOUND.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(Response.error(List.of(error)), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Response<ErrorResponse>> handleException(
            InvalidTokenException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.error(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(Response.error(List.of(error)), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Response<ErrorResponse>> handleException(
            ExpiredTokenException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.error(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(Response.error(List.of(error)), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<ErrorResponse>> handleException(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<String> messages = getMethodArgumentNotValidExceptionErrors(exception);

        List<ErrorResponse> errors = messages.stream().map(message -> ErrorResponse.error(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()))).toList();

        return new ResponseEntity<>(Response.error(errors), HttpStatus.BAD_REQUEST);
    }

    private List<String> getMethodArgumentNotValidExceptionErrors(MethodArgumentNotValidException exception) {
        List<String> validationErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.add(messageSource.getMessage(
                    Objects.requireNonNull(error.getDefaultMessage()),
                    null,
                    LocaleContextHolder.getLocale()));
        });
        return validationErrors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response<ErrorResponse>> handleException(
            UsernameNotFoundException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.error(
                HttpStatus.BAD_REQUEST.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(Response.error(List.of(error)), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response<ErrorResponse>> handleException(BadCredentialsException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.error(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(Response.error(List.of(error)), HttpStatus.UNAUTHORIZED);
    }

}
