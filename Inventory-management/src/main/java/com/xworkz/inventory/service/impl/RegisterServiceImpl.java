package com.xworkz.inventory.service.impl;

import com.xworkz.inventory.dto.LoginDto;
import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import com.xworkz.inventory.service.RegisterService;
import com.xworkz.inventory.util.InventoryUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Override
    public RegisterDTO checkUserBylogin(LoginDto loginDto) {
        System.out.println("Running checkUserByLogin()");

        if (loginDto == null) {
            return null;
        }

        RegisterEntity entity = registerRepo.findEntityByEmail(loginDto.getEmail());

        if (entity == null) {
            System.out.println("Email not found in database");
            return null;
        }

        // Use this if the stored password is BCrypt encoded
        boolean matched = bCryptPasswordEncoder.matches(
                loginDto.getPassword(),
                entity.getPassword()
        );

        if (matched) {
            RegisterDTO dto = new RegisterDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }

        System.out.println("Password does not match");
        return null;
    }


    @Override
    public boolean updateProfile(RegisterDTO registerDTO) {

        System.out.println("RegisterServiceImpl update method is called: " + registerDTO);

        if (registerDTO == null || registerDTO.getId() == 0) {
            return false;
        }

        RegisterEntity registerEntity = new RegisterEntity();

        BeanUtils.copyProperties(registerDTO, registerEntity);

        MultipartFile file = registerDTO.getFile();

        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();

                Path path = Paths.get(InventoryUtil.IMAGE_PATH + fileName);

                Files.write(path, file.getBytes());

                registerEntity.setProfileImage(fileName);
                registerDTO.setProfileImage(fileName);

                System.out.println("File saved successfully: " + fileName);

            } catch (IOException e) {
                throw new RuntimeException("Error saving profile image", e);
            }
        }

        registerRepo.update(registerEntity);

        return true;
    }

    @Override
    public RegisterDTO findUserByEmail(String email) {

        RegisterEntity entity = registerRepo.findEntityByEmail(email);

        if (entity == null) {
            return null;
        }

        RegisterDTO dto = new RegisterDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setPassword(null);

        return dto;
    }
}




