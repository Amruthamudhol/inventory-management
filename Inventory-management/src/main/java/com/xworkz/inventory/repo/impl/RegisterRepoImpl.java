package com.xworkz.inventory.repo.impl;

import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    @Override
    public Boolean emailExists(String email) {
        System.out.println("Checking email: [" + email + "]");

        List<?> result = entityManager
                .createQuery(
                        "SELECT e FROM RegisterEntity e WHERE e.email = :email"
                )
                .setParameter("email", email.trim())
                .getResultList();

        System.out.println("Records found: " + result.size());

        return !result.isEmpty();
    }

    @Override
    public Boolean phoneNumberExists(String phoneNumber) {

        RegisterEntity entity = (RegisterEntity) entityManager
                .createNamedQuery("findpno")
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (entity != null) {
            return true;
        }

        return false;
    }
}
