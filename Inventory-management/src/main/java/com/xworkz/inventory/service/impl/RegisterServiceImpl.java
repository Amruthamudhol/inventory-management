package com.xworkz.inventory.service.impl;

import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import com.xworkz.inventory.service.RegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepo registerRepo;

    @Override
    public void save(RegisterDTO registerDTO) {

        System.out.println("Running save() in RegisterServiceImpl");
        System.out.println("RegisterDTO: " + registerDTO);
        if (registerDTO != null) {

            System.out.println("save RegisterDTO");
            RegisterEntity registerEntity = new RegisterEntity();
            BeanUtils.copyProperties(registerDTO, registerEntity);
            System.out.println("RegisterEntity: " + registerEntity);

            this.registerRepo.save(registerEntity);
        }
    }


}

