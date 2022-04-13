package com.CQCMP.CQCMP.repo;

import com.CQCMP.CQCMP.entity.Allocation;
import com.CQCMP.CQCMP.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetRooms_repo extends JpaRepository<Rooms,String> {
    @Query(value = "SELECT * FROM rooms WHERE isFree=?1", nativeQuery = true)
    List<Rooms> getRooms(int free);

}
