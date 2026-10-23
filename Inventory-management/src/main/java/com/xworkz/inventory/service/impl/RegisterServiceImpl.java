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
    public void save(RegisterDTO registerDTO) {

        System.out.println("Running save() in RegisterServiceImpl");
        System.out.println("RegisterDTO: " + registerDTO);

        if (registerDTO != null) {

            System.out.println("save RegisterDTO");
            RegisterEntity registerEntity = new RegisterEntity();

            String encodedPassword = bCryptPasswordEncoder.encode(registerDTO.getPassword());
            registerDTO.setPassword(encodedPassword);

            BeanUtils.copyProperties(registerDTO, registerEntity);
            System.out.println("RegisterEntity: " + registerEntity);
            registerRepo.save(registerEntity);
        }
    }


}

