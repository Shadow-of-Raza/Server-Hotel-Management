	package com.hotelmanagementsystem.service;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.hotelmanagementsystem.dao.Customer;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CutomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	@Override
	public Customer registerCustomer(@Valid Customer customer) 
	{
		String otp=generateOtp();
		String customeremail;
		customeremail=customer.getCustomeremail();
		sendOtpEmail(customeremail,otp);
		Customer cus=customerRepository.findByEmail(customeremail);
		customer.setValidationotp(otp);
		return  customerRepository.save(customer);
	}

	
	public String generateOtp() {
		Random random=new Random();
		int randomNumber = random.nextInt(999999);
	    String otp = Integer.toString(randomNumber); 
	    while (otp.length() < 6) {
	      otp = "0" + otp;  
	      }
	    return otp;

	}
		private void sendOtpEmail(String toEmail, String otp) {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper= new MimeMessageHelper(message);
	    try {
	    	helper.setTo(toEmail);
	    	helper.setSubject("OTP for Registration");
	    	helper.setText("Your OTP for registration is: "+otp);
	    	javaMailSender.send(message);
	    }catch(MessagingException e) {
	    	e.printStackTrace();
	    }
	    
	}
	
		@Override
		public boolean validateCustomer(String enteredOtp) {
			Customer customer =customerRepository.findByValidateOtp(enteredOtp);
			if(customer!=null) {
				customer.setIsVerified(true);
				customer.setValidationotp(null);
				customerRepository.save(customer);
				sendThankYouEmail(customer.getCustomeremail());
				return true;
			}else {
				return false;
			}
			
		}


	

	@Override
	public void sendThankYouEmail(String customeremail) 
	{

		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
		try {
			helper.setTo(customeremail);
			helper.setSubject("Thank you registering...");
			String htmlMsg="<h3>Dear Customer Thank You for registering with us</h3><br>";
			helper.setText(htmlMsg, true);
			//helper.setText("Dear customer thank you for registering with us...");
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public Customer loginCustomer(String customeremail, String customerpassword) {
		Customer customer=customerRepository.findCustomerByEmail(customeremail);
		 if(customer!=null&&customerpassword.equals(customer.getCustomerpassword())) {
			   return customer;
		 }
		return null;
	}

	
	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}



	@Override
	public Customer getCutomerById(Integer customerid) throws GlobalException 
	{
		Optional<Customer> cus=customerRepository.findById(customerid);
		Customer cus1=null;
		if(!cus.isPresent()) {
			throw new GlobalException("customer with id "+customerid+" not found");
		}
	      cus1=customerRepository.findById(customerid).get();
		return cus1;
	}


	@Override
	public Customer updateCustomerById(Integer customerid, Customer customer) throws GlobalException {
		Optional<Customer> cus=customerRepository.findById(customerid);
		Customer cus1=null;
		if(cus.isPresent()) {
			 cus1=cus.get();
			if(customer.getCustomername()!=null) {
				cus1.setCustomername(customer.getCustomername());
			}
			if(customer.getCustomeremail()!=null) {
				cus1.setCustomeremail(customer.getCustomeremail());
			}
			if(customer.getCustomerphone()!=null) {
				cus1.setCustomerphone(customer.getCustomerphone());
			}
			if(customer.getCustomerdob()!=null) {
				cus1.setCustomerdob(customer.getCustomerdob());
			}
			if(customer.getCustomergender()!=null) {
				cus1.setCustomergender(customer.getCustomergender());
			}
			if(customer.getCustomeraddress()!=null) {
				cus1.setCustomeraddress(customer.getCustomeraddress());
			}
			if(customer.getCustomernationality()!=null) {
				cus1.setCustomernationality(customer.getCustomernationality());
			}
			if(customer.getCustomerpassword()!=null) {
				cus1.setCustomerpassword(customer.getCustomerpassword());
			}
			
		}else {
			throw new GlobalException("Customer with id "+customerid+"not found");
		}
		return customerRepository.save(cus1);
	}
		


	@Override
	public String updateCustomerEmailById(String customeremail, Integer customerid) {
		customerRepository.updateCustomerEmailById(customeremail,customerid);
		Optional<Customer> cus=customerRepository.findById(customerid);
		return "Updated record= " +cus;
	}


	@Override
	public void deleteCustomerById(Integer customerid) throws GlobalException {
		Optional<Customer> cus=customerRepository.findById(customerid);
		Customer cus1=null;
		if(!cus.isPresent()) {
			throw new GlobalException("Customer with id "+customerid+" not found");
		}
		customerRepository.deleteById(customerid);
	}


	@Override
	public Customer getCustomerByEmail(String customeremail) {

		return customerRepository.findCustomerByEmail(customeremail);
	}


	@Override
	public Customer getCustomerByPhone(String customerphone) {
		return customerRepository.findByPhone(customerphone);
	}


	

	@Override
	public Customer updateCustomerPassword(Integer customerid, String newPassword) throws GlobalException{
		Optional<Customer> optionalCustomer = customerRepository.findById(customerid);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setCustomerpassword(newPassword);
            return customerRepository.save(customer);
        } else {
            throw new GlobalException("Customer with ID " + customerid + " not found");
        }
    }
	
	
	@Override
	public void sendEmailToAdmin( String customername, String from, String subject, String body) {
		SimpleMailMessage message=new SimpleMailMessage(); 
		message.setFrom(from);
		message.setTo("guestguru02@gmail.com"); 
		message.setSubject(subject);
		message.setText("Name: " + customername + "\nEmail: " + from + "\n\n" + body); 
		javaMailSender.send(message);
	}
}


	
	
	

