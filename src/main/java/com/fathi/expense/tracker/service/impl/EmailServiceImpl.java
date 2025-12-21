package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String fromEmail = "fatemehfathi.dev@gmail.com";
    private final JavaMailSender mailSender;

    @Override
    public void sendResetPassword(String token, String toEmail) {
        String resetLink = "http://localhost:8080/auth/reset-password-form?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Reset Password");
        message.setText("Click the following link to reset your password:" + resetLink);
        message.setFrom(fromEmail);
        mailSender.send(message);
    }

}
