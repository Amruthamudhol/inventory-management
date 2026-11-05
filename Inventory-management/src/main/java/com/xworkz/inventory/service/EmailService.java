package com.xworkz.inventory.service;

public interface EmailService {
    public Boolean sendEmail(String email, String otp) ;
    Boolean sendEmail(String email, String subject, String body);


}
