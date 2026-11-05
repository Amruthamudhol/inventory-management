package com.xworkz.inventory.service.impl;

import com.xworkz.inventory.configuration.EmailConfig;
import com.xworkz.inventory.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final EmailConfig emailConfig;

    @Override
    public Boolean sendEmail(String email, String otp) {

        if (email != null && otp != null) {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject("OTP for Email Verification");
            message.setText("Your OTP is: " + otp);

            emailConfig.mailSender().send(message);

            log.info("Email sent successfully " + email + " " + otp);

            return true;
        }

        return false;
    }

    @Override
    public Boolean sendEmail(String email, String subject, String body) {

        if (email != null && subject != null && body != null) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);

            emailConfig.mailSender().send(message);

            log.info("Password reset email sent successfully to {}", email);

            return true;
        }

        return false;
    }
}
