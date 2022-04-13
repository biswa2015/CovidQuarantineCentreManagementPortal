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
    @Query(value = "SELECT * FROM allocation WHERE room_id=?1 or student_id=?2",nativeQuery = true)
    Allocation getExistingAllocation(String room_id, String student_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE rooms SET isFree=?2 WHERE room_id=?1",nativeQuery = true)
    void updateRoom(String room_id,int free);

    @Query(value = "SELECT * FROM allocation ", nativeQuery = true)
    List<Allocation> getAllocations();
}
