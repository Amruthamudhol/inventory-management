package com.xworkz.inventory.service.impl;

import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import com.xworkz.inventory.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepo registerRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String save(RegisterDTO registerDTO) {

        System.out.println("Running save() in RegisterServiceImpl");
        System.out.println("RegisterDTO: " + registerDTO);

        if (registerDTO == null) {
            return "Invalid registration details";
        }

        System.out.println("Saving RegisterDTO");

        // Check email
        if (registerRepo.emailExists(registerDTO.getEmail())) {
            System.out.println("Email Id Already Exist");
            return "Email Id Already Exist";
        }

        // Check phone number
        if (registerRepo.phoneNumberExists(registerDTO.getPhoneNumber())) {
            System.out.println("Phone Number Already Exist");
            return "Phone Number Already Exist";
        }

        RegisterEntity registerEntity = new RegisterEntity();

        String encodedPassword = bCryptPasswordEncoder.encode(registerDTO.getPassword());

        registerDTO.setPassword(encodedPassword);
        BeanUtils.copyProperties(registerDTO, registerEntity);

        registerRepo.save(registerEntity);
        System.out.println("Registration successful");

        return "Registration Successful";
    }

    @Override
    public String findEmail(String email) {
        System.out.println("Service received: [" + email + "]");

        Boolean exists = registerRepo.emailExists(email);

        return exists ? "Email Id Already Exist" : "Email Id not Exist";
    }

    @Override
    public String findPhoneNumber(String phoneNumber) {

        System.out.println("Invoking findPhoneNumber:Service " + phoneNumber);
        String status = null;

        if (phoneNumber != null) {

            Boolean isCheck = registerRepo.phoneNumberExists(phoneNumber);
            status = (isCheck) ? "Phone Number Already Exist" : "Phone Number Not Exist";
        }

        return status;
    }
}

