package com.hotelmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.dao.HotelAdmin;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.HotelAdminRepository;

@Service
public class HotelAdminServiceImpl implements HotelAdminService{

	@Autowired
	private HotelAdminRepository hotelAdminRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public HotelAdmin registerHotelAdmin(@Valid HotelAdmin hotelAdmin) {
		String otp=generateOtp();
		String hotelAdminEmail=hotelAdmin.getHoteladminemail();
		sendOtpEmail(hotelAdminEmail,otp);
		HotelAdmin ha=hotelAdminRepository.findHotelAdminByEmail(hotelAdminEmail);
		hotelAdmin.setValidationotp(otp);
		return hotelAdminRepository.save(hotelAdmin);
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
	public boolean validateHotelAdmin(String enteredOtp) {
		 // Find the hotel admin record by OTP in the database
        HotelAdmin hotelAdmin = hotelAdminRepository.findByValidateOtp(enteredOtp);

        if (hotelAdmin != null) {
            hotelAdmin.setIsValidate(true);
            hotelAdmin.setValidationotp(null);
            hotelAdminRepository.save(hotelAdmin);
            sendThankYouEmail(hotelAdmin.getHoteladminemail());
            return true;
        } else {
            return false;
        }
	}

	
	
	@Override
	public void sendThankYouEmail(String hoteladminemail) 
	{
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
		try {
			helper.setTo(hoteladminemail);
			helper.setSubject("Thank you registering...");
			helper.setText("Dear customer thank you for registering with us...");
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public HotelAdmin loginHotelAdmin(String hoteladminemail, String hoteladminpassword) {
		HotelAdmin hotelAdmin=hotelAdminRepository.findHotelAdminByEmail(hoteladminemail);
		if(hotelAdmin!=null && hoteladminpassword.equals(hotelAdmin.getHoteladminpassword())) {
			return hotelAdmin;
		}
		return null;
	}

	@Override
	public List<HotelAdmin> getAllHotelAdmin() {
		return hotelAdminRepository.findAll();
	}
	
	
	
	@Override
	public HotelAdmin getHotelAdminById(Integer hoteladminid) throws GlobalException {
		Optional<HotelAdmin> ha=hotelAdminRepository.findById(hoteladminid);
		HotelAdmin ha1=null;
		if(!ha.isPresent()) {
			throw new GlobalException("Hotel Admin with id "+hoteladminid+" not found");
		}
		ha1=hotelAdminRepository.findById(hoteladminid).get();
		
		return ha1;
	}

	
	
	@Override
	public HotelAdmin updateHotelAdminById(Integer hoteladminid, HotelAdmin hotelAdmin) throws GlobalException {
		Optional<HotelAdmin> ha=hotelAdminRepository.findById(hoteladminid);
		HotelAdmin ha1=null;
		if(ha.isPresent()) {
			ha1=ha.get();
			if(hotelAdmin.getHoteladminname()!=null) {
				ha1.setHoteladminname(hotelAdmin.getHoteladminname());
			}
			if(hotelAdmin.getHoteladminemail()!=null) {
				ha1.setHoteladminemail(hotelAdmin.getHoteladminemail());
			}
			if(hotelAdmin.getHoteladminphonenumber()!=null) {
				ha1.setHoteladminphonenumber(hotelAdmin.getHoteladminphonenumber());
			}
			if(hotelAdmin.getHoteladminpassword()!=null) {
				ha1.setHoteladminpassword(hotelAdmin.getHoteladminpassword());
			}
		}else {
			throw new GlobalException("HotelAdmin with id "+hoteladminid+" not found");
		}
		return hotelAdminRepository.save(ha1);
	}

	@Override
	public void deleteHotelAdminById(Integer hoteladminid) throws GlobalException {
		Optional<HotelAdmin> ha=hotelAdminRepository.findById(hoteladminid);
		HotelAdmin ha1=null;
		if(!ha.isPresent()) {
			throw new GlobalException("Hotel Admin with id "+hoteladminid+" not found");
		}
		hotelAdminRepository.deleteById(hoteladminid);
	}

	@Override
	public HotelAdmin getHotelAdminByEmail(String hoteladminemail) {
		
		return hotelAdminRepository.findHotelAdminByEmail(hoteladminemail);
	}

	@Override
	public HotelAdmin getHotelAdminByPhone(String hoteladminphonenumber) {
		return hotelAdminRepository.findHotelAdminByPhone(hoteladminphonenumber);
	}




	@Override
	public HotelAdmin updateHotelAdminPassword(Integer hoteladminid, String newPassword) throws GlobalException {
		Optional<HotelAdmin> optionalHotelAdmin = hotelAdminRepository.findById(hoteladminid);

        if (optionalHotelAdmin.isPresent()) {
        	HotelAdmin hoteladmin = optionalHotelAdmin.get();
        	hoteladmin.setHoteladminpassword(newPassword);
            return hotelAdminRepository.save(hoteladmin);
        } else {
            throw new GlobalException("Customer with ID " + hoteladminid + " not found");
     
	}
	
	}

	
	
	
	
}
