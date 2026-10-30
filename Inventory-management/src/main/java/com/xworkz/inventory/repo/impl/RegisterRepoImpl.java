package com.xworkz.inventory.repo.impl;

import com.xworkz.inventory.entity.AuditEntity;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class RegisterRepoImpl implements RegisterRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory emf;

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

        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            // Find existing RegisterEntity
            RegisterEntity entity =
                    manager.find(RegisterEntity.class, registerEntity.getId());

            if (entity == null) {
                System.out.println("RegisterEntity not found");
                transaction.rollback();
                return;
            }

            // Update RegisterEntity details
            entity.setName(registerEntity.getName());
            entity.setPhoneNumber(registerEntity.getPhoneNumber());
            entity.setAddress(registerEntity.getAddress());

            // Get existing AuditEntity
            AuditEntity audit = entity.getAuditEntity();

            if (audit == null) {

                // Create new AuditEntity
                audit = new AuditEntity();

                audit.setCreatedBy(entity.getName());
                audit.setCreatedAt(LocalDateTime.now());

                // Establish relationship
                audit.setRegister(entity);
                entity.setAuditEntity(audit);

                manager.persist(audit);
            }

            // Update audit information
            audit.setUpdatedBy(entity.getName());
            audit.setUpdatedAt(LocalDateTime.now());

            // Commit transaction
            transaction.commit();

            System.out.println("RegisterEntity updated successfully");

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {
            manager.close();
        }
    }
}