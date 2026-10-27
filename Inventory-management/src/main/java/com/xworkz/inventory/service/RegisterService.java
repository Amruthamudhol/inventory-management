package com.xworkz.inventory.service;

import com.xworkz.inventory.dto.LoginDto;
import com.xworkz.inventory.dto.RegisterDTO;

public interface RegisterService {
    String save(RegisterDTO registerDTO);
    String findEmail(String email);
    String findPhoneNumber(String phoneNumber);

     RegisterDTO checkUserBylogin(LoginDto loginDto);
     boolean updateProfile(RegisterDTO registerDTO);
    RegisterDTO findUserByEmail(String email);
}
