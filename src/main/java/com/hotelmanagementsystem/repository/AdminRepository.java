package com.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query(value="select * from admin where adminemail=?",nativeQuery = true)
	Admin findAdminByEmail(String adminemail);

}
