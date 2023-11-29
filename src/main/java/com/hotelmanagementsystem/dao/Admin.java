package com.hotelmanagementsystem.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Admin 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer adminid;
	
	@Column(length=40,nullable=false)
	@NotNull(message="Enter admin name")
    private String adminname;
	
	@Column(length = 40, nullable = false, unique = true)
	@NotBlank(message = "Email should not be null and it should be unique. Enter you correct email...")
	//validation for email
	@Email(message = "Invalid Email", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
     private String adminemail;
	
	//validations for password it must greater than 6 letters it must have one upper case letter and one numeric one special character
	@Column(length = 20, nullable = false)
	@Pattern(
		    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{7,}$",
		    message = "Password must be greater than 6 characters and contain at least one uppercase letter, one lowercase letter, one number, and one special character."
		)
     private String adminpassword;
	
	public Admin() {
		super();
	}
	
	public Admin(String adminname, String adminemail,  String adminpassword) {
		super();
		this.adminname = adminname;
		this.adminemail = adminemail;
		this.adminpassword = adminpassword;
	}
	
	
	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	
	public String getAdminemail() {
		return adminemail;
	}
	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
	
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", adminname=" + adminname + ", adminemail=" + adminemail
				+ ", adminpassword=" + adminpassword + "]";
	}
	
	
     
}
