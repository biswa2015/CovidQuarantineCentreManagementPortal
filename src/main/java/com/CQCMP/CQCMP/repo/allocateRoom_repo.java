package com.CQCMP.CQCMP.repo;

import com.CQCMP.CQCMP.entity.Allocation;
import com.CQCMP.CQCMP.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface allocateRoom_repo extends JpaRepository<Allocation,String> {
    @Query(value = "SELECT * FROM allocation WHERE (room_id=?1 or student_id=?2) and vacated=0",nativeQuery = true)
    Allocation getExistingAllocation(String room_id, String student_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE rooms SET isFree=?2 WHERE room_id=?1",nativeQuery = true)
    void updateRoom(String room_id,int free);

    @Transactional
    @Modifying
    @Query(value = "UPDATE allocation SET endDate=?2 , vacated=1 WHERE room_id=?1",nativeQuery = true)
    void updateAllocation(String room_id,String date);

    @Query(value = "SELECT * FROM allocation ", nativeQuery = true)
    List<Allocation> getAllocations();
}
