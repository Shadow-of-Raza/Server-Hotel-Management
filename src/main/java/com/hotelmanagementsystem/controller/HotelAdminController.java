package com.hotelmanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.dao.Admin;
import com.hotelmanagementsystem.dao.Customer;
import com.hotelmanagementsystem.dao.HotelAdmin;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.HotelAdminRepository;
import com.hotelmanagementsystem.service.HotelAdminService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HotelAdminController 
{
	@Autowired
    private	HotelAdminService hotelAdminService;
	
	@Autowired
	private HotelAdminRepository hotelAdminRepository;
	
	//register customer
	@PostMapping("/registerHotelAdmin")
	public ResponseEntity<HotelAdmin> registerHotelAdmin(@Valid @RequestBody HotelAdmin hotelAdmin) {
	    // Check if a hotel admin with the same email already exists
	    HotelAdmin existingAdmin = hotelAdminRepository.findHotelAdminByEmail(hotelAdmin.getHoteladminemail());
	    if (existingAdmin != null) {
	        return ResponseEntity.badRequest().body(null); // Return an error response if already registered
	    }
	    hotelAdminService.registerHotelAdmin(hotelAdmin);
	    return ResponseEntity.status(HttpStatus.CREATED).body(hotelAdmin); // Return the registered hotel admin
	}

	
	
	
	
	@GetMapping("/verifyHotelAdmin/{enteredotp}")
	public boolean verifyHotelAdmin(@PathVariable("enteredotp") String enteredOtp) {
	    boolean otpValidate = hotelAdminService.validateHotelAdmin(enteredOtp);
	    if (otpValidate) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	
	
	//login hotel admin
	@PostMapping("/loginHotelAdmin/{hoteladminemail}/{hoteladminpassword}")
	public HotelAdmin loginHotelAdmin(@PathVariable ("hoteladminemail") String hoteladminemail,@PathVariable ("hoteladminpassword") String hoteladminpassword){
		return hotelAdminService.loginHotelAdmin(hoteladminemail,hoteladminpassword);
	}
	
	
	
	
	
	//get all hotel admin http://localhost:8080/getAllHotelAdmin
	@GetMapping("/getAllHotelAdmin")
    List<HotelAdmin> getAllHotelAdmin()
    {
   	return hotelAdminService.getAllHotelAdmin();
    }
	
	
	
	
	
	//get hotel admin by id http://localhost:8080/getHotelAdminById/1
   @GetMapping("/getHotelAdminById/{hoteladminid}")
   public HotelAdmin getHotelAdminById(@PathVariable ("hoteladminid") Integer hoteladminid) throws GlobalException {
	return hotelAdminService.getHotelAdminById(hoteladminid);
	   
   }
   
   
   
   
   //put mapping for updating record of hoteladmin
 	@PutMapping("/updateHotelAdminById/{hoteladminid}")
 	HotelAdmin updateHotelAdminById(@PathVariable ("hoteladminid") Integer hoteladminid,@RequestBody HotelAdmin hotelAdmin ) throws GlobalException
 	{
 		return hotelAdminService.updateHotelAdminById(hoteladminid,hotelAdmin);
 		
 	}
 	
 	
 	
 	@PutMapping("/updateHotelAdminPassword/{hoteladminid}/{newPassword}")
    public ResponseEntity<HotelAdmin> updateHotelAdminPassword(@PathVariable Integer hoteladminid, @PathVariable String newPassword) throws GlobalException {
 		HotelAdmin updatedhotelAdmin = hotelAdminService.updateHotelAdminPassword(hoteladminid, newPassword);
        return ResponseEntity.ok(updatedhotelAdmin);
    }
    
 	
 	
 	
 	
 //delete mapping for deleting hotel admin by id
 	@DeleteMapping("/deleteHotelAdminById/{hoteladminid}")
 	String deleteHotelAdminById(@PathVariable ("hoteladminid") Integer hoteladminid) throws GlobalException
 	{
 		hotelAdminService.deleteHotelAdminById(hoteladminid);
 		return "Hotel Admin deleted";
 	}
 	
 	
 	
 	
 	
 	
 	//get hotel admin by email
 	@GetMapping("/getHotelAdminByEmail/{hoteladminemail}")
 	public HotelAdmin getHotelAdminByEmail(@PathVariable ("hoteladminemail") String hoteladminemail)
 	{
 		return hotelAdminService.getHotelAdminByEmail(hoteladminemail);
 	}
 	
 	
 	
 	
 	//get hotel admin by phone
 	@GetMapping("/getHotelAdminByPhone/{hoteladminphonenumber}")
 	public HotelAdmin getHotelAdminByPhone(@PathVariable ("hoteladminphonenumber") String hoteladminphonenumber)
 	{
 		return hotelAdminService.getHotelAdminByPhone(hoteladminphonenumber);
 	}
}
