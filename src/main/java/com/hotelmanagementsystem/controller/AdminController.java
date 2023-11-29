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
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.dao.Admin;
import com.hotelmanagementsystem.dao.Customer;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin)
    {
   	 Admin ad=adminService.registerAdmin(admin);
   	 return new  ResponseEntity<Admin>(ad,HttpStatus.CREATED);
    }
	
	//login admin using email and password
	@PostMapping("/loginAdmin/{adminemail}/{adminpassword}")
	public Admin loginAdmin(@PathVariable ("adminemail") String adminemail,@PathVariable ("adminpassword") String adminpassword) {
		return adminService.loginAdmin(adminemail,adminpassword);
	}
	
	@GetMapping("/getAllAdmin")
	List<Admin> getAllAdmin(){
		return adminService.getAllAdmin();
	}
	
	@GetMapping("/getAdminById/{adminid}")
	public Admin getAdminById(@PathVariable ("adminid") Integer adminid) throws GlobalException
	{
		return adminService.getAdminById(adminid);
	}
	
	@GetMapping("/getAdminByEmail/{adminemail}")
	public Admin getAdminByEmail(@PathVariable ("adminemail") String adminemail)
	{
		return adminService.getAdminByEmail(adminemail);
	}
	

    @PutMapping("/updateAdminPassword/{adminid}/{newPassword}")
    public ResponseEntity<Admin> updateAdminPassword(@PathVariable Integer adminid, @PathVariable String newPassword) throws GlobalException {
        Admin updatedAdmin = adminService.updateAdminPassword(adminid, newPassword);
        return ResponseEntity.ok(updatedAdmin);
    }
    
	
	@PutMapping("/updateAdminById/{adminid}")
	Admin updateAdminById(@PathVariable ("adminid") Integer adminid, @RequestBody Admin admin) throws GlobalException
	{
		return adminService.updateAdminById(adminid,admin);
	}
	@DeleteMapping("/deleteAdminById/{adminid}")
	String deleteAdminById(@PathVariable ("adminid") Integer adminid) throws GlobalException
	{
		adminService.deleteAdminById(adminid);
		return "Admin deleted";
	}
	

	//////////////////////
	
	
	
	
}
