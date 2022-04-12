package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tests")
@Data
public class Test {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String test_id;

    @Column(name="student_id")
    private String student_id;

    @Column(name="result")
    private String result;
}
