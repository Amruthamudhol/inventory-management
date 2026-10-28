package com.xworkz.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Data
@Table(name = "audit_entity")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", referencedColumnName = "id", unique = true)
    private RegisterEntity register;
}
