package com.CQCMP.CQCMP.repo;

import com.CQCMP.CQCMP.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddMember_repo extends JpaRepository<Students,String> {
    @Query(value = "SELECT * FROM students WHERE emailID=?1 or roll_num=?2 or contact=?3",nativeQuery = true)
    Students getStudentByEmail(String email,String roll,String contact);

    @Query(value = "SELECT emailID FROM students WHERE student_id=?1",nativeQuery = true)
    String getEmail(String studentID);
}

