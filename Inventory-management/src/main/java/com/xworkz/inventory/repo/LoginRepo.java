package com.xworkz.inventory.repo;

import com.xworkz.inventory.entity.RegisterEntity;
import java.util.Optional;

public interface LoginRepo {

    Optional<RegisterEntity> findByEmail(String email);
}