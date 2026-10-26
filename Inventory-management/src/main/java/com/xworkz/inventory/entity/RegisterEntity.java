package com.xworkz.inventory.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "register_details")

@NamedQuery(
        name = "getEmailId",
        query = "SELECT e FROM RegisterEntity e WHERE e.email = :email"
)
@NamedQuery(name = "findpno", query = "SELECT e FROM RegisterEntity e WHERE e.phoneNumber = :phoneNumber")

public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private String role;
}