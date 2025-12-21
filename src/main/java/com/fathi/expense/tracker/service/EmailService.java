package com.fathi.expense.tracker.service;

public interface EmailService {

    void sendResetPassword(String token,String toEmail);

}
