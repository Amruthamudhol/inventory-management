package com.xworkz.inventory.repo.impl;

import com.xworkz.inventory.entity.AuditEntity;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class RegisterRepoImpl implements RegisterRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(RegisterEntity registerEntity) {

        System.out.println("RegisterRepoImpl save method is called");

        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setCreatedBy(registerEntity.getName());
        auditEntity.setCreatedAt(LocalDateTime.now());

        registerEntity.setAuditEntity(auditEntity);
        auditEntity.setRegister(registerEntity);

        entityManager.persist(registerEntity);

        System.out.println("Data saved to DB: " + registerEntity.getName());
    }

    @Override
    public Boolean emailExists(String email) {

        System.out.println("Checking email: [" + email + "]");

        List<?> result = entityManager
                .createQuery("SELECT e FROM RegisterEntity e WHERE e.email = :email")
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

        return entity != null;
    }

    @Override
    public RegisterEntity findEntityByEmail(String email) {

        System.out.println("Finding email: [" + email + "]");

        try {
            return (RegisterEntity) entityManager
                    .createNamedQuery("getEmailId")
                    .setParameter("email", email.trim())
                    .getSingleResult();

        } catch (javax.persistence.NoResultException e) {
            System.out.println("Email not found in database");
            return null;
        }
    }

    @Override
    public void update(RegisterEntity registerEntity) {

        RegisterEntity registerEntity1 = entityManager.find(RegisterEntity.class, registerEntity.getId());

        if (registerEntity1 == null) {
            System.out.println("RegisterEntity not found");
            return;
        }

        registerEntity1.setName(registerEntity.getName());
        registerEntity1.setPhoneNumber(registerEntity.getPhoneNumber());
        registerEntity1.setAddress(registerEntity.getAddress());


        AuditEntity auditEntity = entityManager.createQuery("SELECT a FROM AuditEntity a WHERE a.register.id = :registerId", AuditEntity.class)
                .setParameter("registerId", registerEntity1.getId())
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (auditEntity != null) {
            auditEntity.setUpdatedAt(LocalDateTime.now());
            auditEntity.setUpdatedBy(registerEntity1.getName());
        } else {
            auditEntity = new AuditEntity();
            auditEntity.setCreatedBy(registerEntity1.getName());
            auditEntity.setCreatedAt(LocalDateTime.now());
            auditEntity.setUpdatedBy(registerEntity1.getName());
            auditEntity.setUpdatedAt(LocalDateTime.now());

            // Link to the existing managed register entity
            auditEntity.setRegister(registerEntity1);
            registerEntity1.setAuditEntity(auditEntity);

            entityManager.persist(auditEntity);
        }

    }
}