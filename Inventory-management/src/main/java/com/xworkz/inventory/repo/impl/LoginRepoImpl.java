package com.xworkz.inventory.repo.impl;

import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.LoginRepo;

import java.util.Optional;

public class LoginRepoImpl implements LoginRepo {

    @Override
    public Optional<RegisterEntity> findByEmail(String email) {
        return Optional.empty();
    }
}
