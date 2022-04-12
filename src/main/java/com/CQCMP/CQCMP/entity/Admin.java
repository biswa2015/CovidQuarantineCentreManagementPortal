package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="admin_info")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String adminID;
    private String emailID;
    private String password;
    private String name;
}
