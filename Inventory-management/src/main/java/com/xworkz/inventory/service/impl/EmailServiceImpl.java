package com.xworkz.inventory.service.impl;

import com.xworkz.inventory.configuration.EmailConfig;
import com.xworkz.inventory.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailConfig emailConfig;

    @Override
    public void sendEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("OTP for Email Verification");
        message.setText("Your OTP is: " + otp);

        emailConfig.mailSender().send(message);

        System.out.println("OTP sent successfully " + email + " " + otp);
    }
}
