package com.CQCMP.CQCMP.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="rooms")
@Data
public class Rooms {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String room_id;

    @Column(name="roomNumber")
    private int roomNum;

    @Column(name="floorNumber")
    private int floorNum;

    @Column(name="isFree")
    private int freeRoom;
}
