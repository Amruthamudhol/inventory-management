package com.xworkz.inventory.repo;

import com.xworkz.inventory.entity.RegisterEntity;

public interface RegisterRepo {
    void save(RegisterEntity registerEntity);
    Boolean emailExists(String email);
}
