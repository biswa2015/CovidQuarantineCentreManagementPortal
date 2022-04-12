package com.CQCMP.CQCMP.repo;

import com.CQCMP.CQCMP.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface adminLogin_repo extends JpaRepository<Admin,String> {
    @Query(value = "SELECT * FROM admin_info WHERE admin_id=?1",nativeQuery = true)
    Admin getAdminById(String id);

    @Query(value = "SELECT * FROM admin_info WHERE emailid=?1",nativeQuery = true)
    Admin getAdminByEmail(String email);
}
