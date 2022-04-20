package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="students")
@Data
public class Students {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String student_id;

    @Column(name="emailID")
    private String email_id;

    @Column(name="contact")
    private String contact;

    @Column(name="roll_num")
    private String rollNum;

    @Column(name="sname")
    private String StudentName;
}
