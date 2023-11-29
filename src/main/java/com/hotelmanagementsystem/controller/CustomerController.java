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

import com.hotelmanagementsystem.dao.Customer;
import com.hotelmanagementsystem.dao.EmailRequest;
import com.hotelmanagementsystem.dao.HotelAdmin;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.CustomerRepository;
import com.hotelmanagementsystem.service.CutomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController 
{
	@Autowired
    public CutomerService customerservice;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Registraration for customer http://localhost:8080/registerCustomer
    
	@PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer)
    {
    	Customer existingCustomer = customerRepository.findCustomerByEmail(customer.getCustomeremail());
	    if (existingCustomer != null) {
	        return ResponseEntity.badRequest().body(null); // Return an error response if already registered
	    }
   	 customerservice.registerCustomer(customer);
   	 return ResponseEntity.status(HttpStatus.CREATED).body(customer); // Return the registered hotel customer
    }
    
    
    
    //verify customer by entering otp
    @GetMapping("/verifyCustomer/{enteredotp}")
    public boolean verifyCustomer(@PathVariable ("enteredotp") String enteredOtp){
    	boolean otpValidate=customerservice.validateCustomer(enteredOtp);
    	if (otpValidate) {
	        return true;
	    } else {
	        return false;
	    }
    }
    
    
    
  //login customer using email and password.
    @PostMapping("/loginCustomer/{customeremail}/{customerpassword}")
    public  Customer  loginCustomer(@PathVariable("customeremail")String customeremail,@PathVariable("customerpassword") String customerpassword){
    	return customerservice.loginCustomer(customeremail,customerpassword);
    }
    
    
    
    
    //get all customer  http://localhost:8080/getAllCustomer
    @GetMapping("/getAllCustomer")
    List<Customer> getAllStudent()
    {
   	return customerservice.getAllCustomer();
    }
    
    
    
    
    //get customer by id   http://localhost:8080/getCustomerById/1
    @GetMapping("/getCustomerById/{customerid}")
    public Customer getCutomerById(@PathVariable("customerid") Integer customerid) throws GlobalException {
		
		 return customerservice.getCutomerById(customerid);
	  }
 
    @PutMapping("/updateCustomerPassword/{customerid}/{newPassword}")
    public ResponseEntity<Customer> updateCustomerPassword(@PathVariable Integer customerid, @PathVariable String newPassword) throws GlobalException {
        Customer updatedCustomer = customerservice.updateCustomerPassword(customerid, newPassword);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    
  //put mapping for update record of customer by id
  	@PutMapping("/updateCustomerById/{customerid}")
  	Customer updateCustomerById(@PathVariable ("customerid") Integer customerid,@RequestBody Customer customer ) throws GlobalException
  	{
  		return customerservice.updateCustomerById(customerid,customer);
  		
  	}
  	
  	//update customer email by id
    @PutMapping("/updateCustomerEmailById/{customeremail}/{customerid}")
    public String updateCustomerEmailById(@PathVariable ("customeremail") String customeremail,@PathVariable ("customerid") Integer customerid)
    {
		return customerservice.updateCustomerEmailById(customeremail,customerid);
    	
    }
    
    //delete customer by id
    @DeleteMapping("/deleteCustomerById/{customerid}")
    String deleteCustomerById(@PathVariable ("customerid") Integer customerid) throws GlobalException
    {
    	customerservice.deleteCustomerById(customerid);
		return "Customer deleted";
    	
    }
    
    //get customer by email
    @GetMapping("/getCustomerByEmail/{customeremail}")
    public Customer getCustomerByEmail(@PathVariable ("customeremail") String customeremail)
    {
    	return customerservice.getCustomerByEmail(customeremail);
    }
    
    //get customer by phonenumber
    @GetMapping("/getCustomerByPhone/{customerphone}")
    public Customer getCustomerByPhone(@PathVariable ("customerphone") String customerphone)
    {
    	return customerservice.getCustomerByPhone(customerphone);
    }
    
    @PostMapping("/sendEmailToAdmin")
    public void sendEmailToAdmin(@RequestBody EmailRequest emailRequest)
    { 	
    	String customername=emailRequest.getCustomername(); 
    	String from=emailRequest.getFrom();  
    	String subject=emailRequest.getSubject();
    	String body=emailRequest.getBody();
    	customerservice.sendEmailToAdmin(customername,from, subject, body);
    }
    
}
