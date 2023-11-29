package com.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.HotelAdmin;

@Repository
public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Integer>{
    
	
	@Query(value="SELECT * FROM HotelAdmin WHERE validationOtp = :enteredOtp",nativeQuery = true)
	HotelAdmin findByValidateOtp(@Param("enteredOtp") String enteredOtp);

    @Query(value="select hoteladminemail from hoteladmin where hoteladminemail=?",nativeQuery = true)
	HotelAdmin findByEmail(String hotelAdminEmail);
    
    @Query(value="select * from hoteladmin where hoteladminemail=?",nativeQuery = true)
	HotelAdmin findHotelAdminByEmail(String hoteladminemail);

    
    @Query(value="select * from hoteladmin where hoteladminphonenumber=?",nativeQuery = true)
	HotelAdmin findHotelAdminByPhone(String hoteladminphonenumber);

}
