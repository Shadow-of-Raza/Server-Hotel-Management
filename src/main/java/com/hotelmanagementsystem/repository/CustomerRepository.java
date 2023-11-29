package com.hotelmanagementsystem.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value="Select customeremail from customer  where customeremail=?",nativeQuery = true)
	Customer findByEmail(String customeremail);
    
	
	
	@Query(value="select * from customer  where validationotp=?",nativeQuery = true)
	Customer findByValidateOtp( String enteredOtp);
    
	
	@Transactional
	@Modifying
    @Query(value="update customer set customeremail=?1 where customerid=?2",nativeQuery = true)
	void updateCustomerEmailById(String customeremail, Integer customerid);


	@Query(value="select * from customer where customeremail=?",nativeQuery = true)
	Customer findCustomerByEmail(String customeremail);


	@Query(value="select * from customer where customerphone=?",nativeQuery = true)
	Customer findByPhone(String customerphone);

}
