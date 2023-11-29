package com.hotelmanagementsystem.dao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hoteladmin")
public class HotelAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hoteladminid;

    @Column(length = 50, nullable = false)
    private String hoteladminname;

    @Column(length = 40, nullable = false, unique = true)
    @NotBlank(message = "Email should not be null and it should be unique. Enter your correct email...")
    @Email(message = "Invalid Email", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    private String hoteladminemail;

    @Column(length = 10, nullable = false, unique = true)
    @Pattern(message = "Invalid Phone Number", regexp = "^[6-9]\\d{9}$")
    private String hoteladminphonenumber;

    @Column(nullable = true)
    private String validationotp;

    private Boolean isValidate;

    @Column(length = 200, nullable = false)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{7,}$",
            message = "Password must be greater than 6 characters and contain at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String hoteladminpassword;

    @JsonIgnore
    // OneToOne relation with HotelInformation
    @OneToOne(mappedBy = "hoteladmin", cascade = CascadeType.ALL)
    private HotelInformation hotelinformation;

	public HotelAdmin() {
		super();
	}
	public HotelAdmin(String hoteladminname, String hoteladminemail, String hoteladminphonenumber, String validationotp,
			Boolean isValidate, String hoteladminpassword){
		super();
		this.hoteladminname = hoteladminname;
		this.hoteladminemail = hoteladminemail;
		this.hoteladminphonenumber = hoteladminphonenumber;
		this.validationotp = validationotp;
		this.isValidate = isValidate;
		this.hoteladminpassword = hoteladminpassword;
	}


	public Integer getHoteladminid() {
		return hoteladminid;
	}


	public void setHoteladminid(Integer hoteladminid) {
		this.hoteladminid = hoteladminid;
	}


	public String getHoteladminname() {
		return hoteladminname;
	}


	public void setHoteladminname(String hoteladminname) {
		this.hoteladminname = hoteladminname;
	}


	public String getHoteladminemail() {
		return hoteladminemail;
	}


	public void setHoteladminemail(String hoteladminemail) {
		this.hoteladminemail = hoteladminemail;
	}


	public String getHoteladminphonenumber() {
		return hoteladminphonenumber;
	}


	public void setHoteladminphonenumber(String hoteladminphonenumber) {
		this.hoteladminphonenumber = hoteladminphonenumber;
	}


	public String getValidationotp() {
		return validationotp;
	}


	public void setValidationotp(String validationotp) {
		this.validationotp = validationotp;
	}


	public Boolean getIsValidate() {
		return isValidate;
	}


	public void setIsValidate(Boolean isValidate) {
		this.isValidate = isValidate;
	}


	public String getHoteladminpassword() {
		return hoteladminpassword;
	}


	public void setHoteladminpassword(String hoteladminpassword) {
		this.hoteladminpassword = hoteladminpassword;
	}

	public HotelInformation getHotelinformation() {
		return hotelinformation;
	}
	public void setHotelinformation(HotelInformation hotelinformation) {
		this.hotelinformation = hotelinformation;
	}
	
	@Override
	public String toString() {
		return "HotelAdmin [hoteladminid=" + hoteladminid + ", hoteladminname=" + hoteladminname + ", hoteladminemail="
				+ hoteladminemail + ", hoteladminphonenumber=" + hoteladminphonenumber + ", validationotp="
				+ validationotp + ", isValidate=" + isValidate + ", hoteladminpassword=" + hoteladminpassword
				+ ", hotelinformation=" + hotelinformation + "]";
	}
	
}
	
	
	
	

