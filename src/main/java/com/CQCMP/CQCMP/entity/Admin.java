package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="admin_info")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String admin_ID;

    @Column(name="emailid")
    private String emailID;

    @Column(name="adminpassword")
    private String password;

    @Column(name="adminname")
    private String name;
}
