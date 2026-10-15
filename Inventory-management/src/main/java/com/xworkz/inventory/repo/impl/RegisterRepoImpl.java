package com.xworkz.inventory.repo.impl;

import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class RegisterRepoImpl implements RegisterRepo {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(RegisterEntity registerEntity) {
        System.out.println("Running save() in RegisterRepoImpl");
        System.out.println("RegisterEntity:"+registerEntity);
        this.entityManager.persist(registerEntity);
    }
}
