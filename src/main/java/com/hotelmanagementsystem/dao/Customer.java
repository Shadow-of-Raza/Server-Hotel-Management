package com.hotelmanagementsystem.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Entity
public class Customer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerid;
	
	@Column(length = 50, nullable = false)
	private String customername;
	
	@Column(length = 40, nullable = false, unique = true)
	@NotBlank(message = "Email should not be null and it should be unique. Enter you correct email...")
	//validation for email
	@Email(message = "Invalid Email", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String customeremail;
	
	@Column(length = 10, nullable = false, unique = true)
	//Validation for phone number starts from 6to9 and another 9 digits
	@Pattern(message = "Invalid Phone Number", regexp = "^[6-9]\\d{9}$")
	private String customerphone;
	
	// DOB // Multi-valued attribute
	@Column(nullable = false)
	@Past
	private Date customerdob;
	
	@Column(nullable = true)
	@NotNull(message = "Enter your Gender")
	private String customergender;
	
	// Address // Multi-valued attribute
	//@Embedded
	private Address customeraddress;
	
	@Column(length = 35, nullable = false)
	@NotNull(message = "Enter your Nationality")
	private String customernationality;
	
	@Column(nullable = true)
	private String validationotp;
	
	private Boolean isVerified;
	@Column(length = 200, nullable = false)
	//validations for password it must greater than 6 letters it must have one upper case letter and one numeric one special character

	@Pattern(
		    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{7,}$",
		    message = "Password must be greater than 6 characters and contain at least one uppercase letter, one lowercase letter, one number, and one special character."
		)
	private String customerpassword;

	// Relations
	
	// addhar number
	// profile photo 
	
	public Customer() {
		super();
	}

	public Customer(String customername, String customeremail, String customerphone, Date customerdob,
			String customergender, Address customeraddress, String customernationality,String validationotp,Boolean isVerified ,String customerpassword) {
		super();
		this.customername = customername;
		this.customeremail = customeremail;
		this.customerphone = customerphone;
		this.customerdob = customerdob;
		this.customergender = customergender;
		this.customeraddress = customeraddress;
		this.customernationality = customernationality;
		this.validationotp = validationotp;
		this.isVerified=isVerified;
		this.customerpassword = customerpassword;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomeremail() {
		return customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public String getCustomerphone() {
		return customerphone;
	}

	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}

	public Date getCustomerdob() {
		return customerdob;
	}

	public void setCustomerdob(Date customerdob) {
		this.customerdob = customerdob;
	}

	public String getCustomergender() {
		return customergender;
	}

	public void setCustomergender(String customergender) {
		this.customergender = customergender;
	}

	public Address getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(Address customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getCustomernationality() {
		return customernationality;
	}

	public void setCustomernationality(String customernationality) {
		this.customernationality = customernationality;
	}

	public String getValidationotp() {
		return validationotp;
	}

	public void setValidationotp(String validationotp) {
		this.validationotp = validationotp;
	}

	public String getCustomerpassword() {
		return customerpassword;
	}

	public void setCustomerpassword(String customerpassword) {
		this.customerpassword = customerpassword;
	}

	
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername=" + customername + ", customeremail="
				+ customeremail + ", customerphone=" + customerphone + ", customerdob=" + customerdob
				+ ", customergender=" + customergender + ", customeraddress=" + customeraddress
				+ ", customernationality=" + customernationality + ", validationotp=" + validationotp + ", isVerified="
				+ isVerified + ", customerpassword=" + customerpassword + "]";
	}

		
	
	
	
	
}
