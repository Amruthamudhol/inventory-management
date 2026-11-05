package com.xworkz.inventory.service.impl;

import com.xworkz.inventory.dto.LoginDto;
import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import com.xworkz.inventory.service.EmailService;
import com.xworkz.inventory.service.RegisterService;
import com.xworkz.inventory.util.InventoryUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegisterRepo registerRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String save(RegisterDTO registerDTO) {

        log.info("Running save() in RegisterServiceImpl");
        log.info("RegisterDTO: " + registerDTO);

        if (registerDTO == null) {
            return "Invalid registration details";
        }

        // Check email
        if (registerRepo.emailExists(registerDTO.getEmail())) {
            return "Email Id Already Exist";
        }

        // Check phone number
        if (registerRepo.phoneNumberExists(registerDTO.getPhoneNumber())) {
            return "Phone Number Already Exist";
        }

        RegisterEntity registerEntity = new RegisterEntity();

        String encodedPassword =
                bCryptPasswordEncoder.encode(registerDTO.getPassword());

        registerDTO.setPassword(encodedPassword);

        // Set default profile image
        registerDTO.setProfileImage("default.png");

        BeanUtils.copyProperties(registerDTO, registerEntity);

        registerRepo.save(registerEntity);
        log.info("Registration successful");
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

        RegisterEntity entity =
                registerRepo.findEntityByEmail(loginDto.getEmail());

        if (entity == null) {
            System.out.println("Email not found");
            return null;
        }

        // Check if account is already blocked
        if (entity.isBlocked()) {
            System.out.println("Account is blocked");
            return null;
        }

        boolean matched = bCryptPasswordEncoder.matches(
                loginDto.getPassword(),
                entity.getPassword()
        );

        if (matched) {

            entity.setAttempts(0);
            registerRepo.update(entity);

            RegisterDTO dto = new RegisterDTO();
            BeanUtils.copyProperties(entity, dto);

            return dto;

        } else {

            // Read the existing attempts count
            int attempts = entity.getAttempts() == null
                    ? 0
                    : entity.getAttempts();

            // Increment failed attempts
            attempts++;
            entity.setAttempts(attempts);

            System.out.println("Failed attempts: " + attempts);

            // Block after 3 incorrect passwords
            if (attempts >= 3) {
                entity.setBlocked(true);
                System.out.println("Account blocked. Reset password required.");
            }

            registerRepo.update(entity);

            return null;
        }
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


    @Override
    public Boolean sendEmail(String email) {
        System.out.println("RegisterServiceImpl sendEmail method is called " + email);

        if (email != null) {
            Boolean isCheck = registerRepo.emailExists(email);

            if (isCheck) {

                String otp = InventoryUtil.generateOtp();
                Boolean isSent = emailService.sendEmail(email, otp);

                if (isSent) {
                    Boolean isSaved = registerRepo.saveOtp(email, otp);
                    if (isSaved) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    @Override
    public RegisterDTO validateOtp(String email, String otp) {

        System.out.println("Invoking validateOtp: " + email + " " + otp);
        RegisterDTO registerDTO = null;
        if (email != null && otp != null) {

            RegisterEntity entity = registerRepo.findEntityByEmail(email);

            if (entity != null) {
                if (entity.getOtp().equals(otp)) {
                    registerDTO = new RegisterDTO();
                    BeanUtils.copyProperties(entity, registerDTO);
                }
            }
        }

        return registerDTO;
    }

    @Override
    public boolean sendResetLink(String email) {
        RegisterEntity entity = registerRepo.findEntityByEmail(email);

        if (entity == null) {
            log.warn("Email not found: {}", email);
            return false;
        }

        String token = InventoryUtil.generateResetToken();

        entity.setResetToken(token);
        entity.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
        registerRepo.update(entity);

        String resetUrl =
                "http://localhost:8080/Inventory-management/register/resetPassword?token=" + token;

        String body = "Click the link below to reset your password:\n" + resetUrl
                + "\nThis link expires in 15 minutes.";

        return emailService.sendEmail(
                email,
                "Password Reset",
                body
        );
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {

        RegisterEntity entity = registerRepo.findEntityByResetToken(token);

        if (entity == null) {
            return false;
        }

        // Check token expiry
        if (entity.getResetTokenExpiry() == null
                || entity.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Update password and unblock account
        entity.setPassword(bCryptPasswordEncoder.encode(newPassword));
        entity.setAttempts(0);
        entity.setBlocked(false);

        // Invalidate token so it cannot be reused
        entity.setResetToken(null);
        entity.setResetTokenExpiry(null);

        registerRepo.update(entity);

        return true;
    }
}




