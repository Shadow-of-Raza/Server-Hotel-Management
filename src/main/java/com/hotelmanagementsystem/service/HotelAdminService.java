package com.hotelmanagementsystem.service;


import java.util.List;

import com.hotelmanagementsystem.dao.HotelAdmin;
import com.hotelmanagementsystem.error.GlobalException;

public interface HotelAdminService {

	 public HotelAdmin registerHotelAdmin( HotelAdmin hotelAdmin);

	public void sendThankYouEmail(String hoteladminemail);

	public List<HotelAdmin> getAllHotelAdmin();

	public HotelAdmin getHotelAdminById(Integer hoteladminid) throws GlobalException;

	

	public HotelAdmin updateHotelAdminById(Integer hoteladminid, HotelAdmin hotelAdmin) throws GlobalException;

	public void deleteHotelAdminById(Integer hoteladminid) throws GlobalException;

	public HotelAdmin getHotelAdminByEmail(String hoteladminemail);

	public HotelAdmin getHotelAdminByPhone(String hoteladminphonenumber);

	public HotelAdmin loginHotelAdmin(String hoteladminemail, String hoteladminpassword);

	public boolean validateHotelAdmin(String enteredOtp);

	public HotelAdmin updateHotelAdminPassword(Integer hoteladminid, String newPassword) throws GlobalException;



}
