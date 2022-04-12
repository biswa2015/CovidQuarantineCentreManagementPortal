package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="students")
@Data
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String student_id;
    private String email_id;
    private String contact;
    private String rollNum;
}
