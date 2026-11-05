package com.xworkz.inventory.repo.impl;

import com.xworkz.inventory.entity.AuditEntity;
import com.xworkz.inventory.entity.RegisterEntity;
import com.xworkz.inventory.repo.RegisterRepo;
import com.xworkz.inventory.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class RegisterRepoImpl implements RegisterRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private EmailService emailService;

    @Override
    public void save(RegisterEntity registerEntity) {

        log.info("RegisterRepoImpl save method is called");

        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setCreatedBy(registerEntity.getName());
        auditEntity.setCreatedAt(LocalDateTime.now());

        registerEntity.setAuditEntity(auditEntity);
        auditEntity.setRegister(registerEntity);

        entityManager.persist(registerEntity);

        log.info("Data saved to DB: " + registerEntity.getName());
    }

    @Override
    public Boolean emailExists(String email) {

        log.info("Checking email: [" + email + "]");

        List<?> result = entityManager
                .createQuery("SELECT e FROM RegisterEntity e WHERE e.email = :email")
                .setParameter("email", email.trim())
                .getResultList();

        log.info("Records found: " + result.size());

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

        log.info("Finding email: [" + email + "]");

        try {
            return (RegisterEntity) entityManager
                    .createNamedQuery("getEmailId")
                    .setParameter("email", email.trim())
                    .getSingleResult();

        } catch (javax.persistence.NoResultException e) {
            log.error("Email not found in database");
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

            // Update profile details
            entity.setName(registerEntity.getName());
            entity.setPhoneNumber(registerEntity.getPhoneNumber());
            entity.setAddress(registerEntity.getAddress());

            entity.setResetToken(registerEntity.getResetToken());
            entity.setResetTokenExpiry(registerEntity.getResetTokenExpiry());

            // Update profile image only when a new image is provided
            if (registerEntity.getProfileImage() != null
                    && !registerEntity.getProfileImage().isEmpty()) {
                entity.setProfileImage(registerEntity.getProfileImage());
            }

            // Update login security fields
            entity.setAttempts(registerEntity.getAttempts() == null
                    ? 0
                    : registerEntity.getAttempts());

            entity.setBlocked(registerEntity.isBlocked());

            // Get existing AuditEntity
            AuditEntity audit = entity.getAuditEntity();

            if (audit == null) {
                audit = new AuditEntity();

                audit.setCreatedBy(entity.getName());
                audit.setCreatedAt(LocalDateTime.now());

                audit.setRegister(entity);
                entity.setAuditEntity(audit);

                manager.persist(audit);
            }

            // Update audit information
            audit.setUpdatedBy(entity.getName());
            audit.setUpdatedAt(LocalDateTime.now());

            transaction.commit();

            log.info("RegisterEntity updated successfully");

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {
            manager.close();
        }
    }

    @Override
    public Boolean saveOtp(String email, String otp) {

        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            int result = manager.createNamedQuery("updatingOtpByEmail")
                    .setParameter("otp", otp)
                    .setParameter("email", email)
                    .executeUpdate();

            transaction.commit();

            return result > 0;

        } catch (PersistenceException e) {
            e.printStackTrace();

            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            manager.close();
        }

        return false;
    }

    @Override
    public RegisterEntity findEntityByResetToken(String resetToken) {

        EntityManager manager = emf.createEntityManager();

        try {
            return manager.createQuery("SELECT r FROM RegisterEntity r WHERE r.resetToken = :token", RegisterEntity.class)
                    .setParameter("token", resetToken)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

        } finally {
            manager.close();
        }
    }
}