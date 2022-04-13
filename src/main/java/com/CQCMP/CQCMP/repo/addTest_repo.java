package com.CQCMP.CQCMP.repo;

import com.CQCMP.CQCMP.entity.Rooms;
import com.CQCMP.CQCMP.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface addTest_repo extends JpaRepository<Test,String> {
    @Query(value = "SELECT * FROM tests WHERE student_id=?1",nativeQuery = true)
    Test getTestByStudent(String student_id);

    @Query(value = "SELECT * FROM tests WHERE result=?1", nativeQuery = true)
    List<Test> getPositives(String result);
}
