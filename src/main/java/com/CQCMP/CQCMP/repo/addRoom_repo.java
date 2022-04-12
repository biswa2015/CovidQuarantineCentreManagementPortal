package com.CQCMP.CQCMP.repo;

import com.CQCMP.CQCMP.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface addRoom_repo extends JpaRepository<Rooms,String> {
    @Query(value = "SELECT * FROM rooms WHERE roomNumber=?1 and floorNumber=?2",nativeQuery = true)
    Rooms getRoomsByRoomAndFloor(int roomNum,int floorNum);
}
