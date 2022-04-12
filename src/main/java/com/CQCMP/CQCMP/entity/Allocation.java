package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="allocation")
@Data
public class Allocation {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String alloc_id;
    private String student_id;
    private String room_id;
}
