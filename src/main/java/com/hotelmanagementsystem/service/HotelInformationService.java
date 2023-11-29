package com.hotelmanagementsystem.service;

import java.util.List;


import com.hotelmanagementsystem.dao.HotelInformation;
import com.hotelmanagementsystem.error.GlobalException;

public interface HotelInformationService {

	public HotelInformation addHotelInformation(HotelInformation hotelInformation);

	public HotelInformation setHotelAdminidToHotelInformation(Integer hotelid, Integer hoteladminid) throws GlobalException;

	public List<HotelInformation> getAllHotelInformation();

	public HotelInformation getHotelInformationById(Integer hotelid) throws GlobalException;

	public HotelInformation updateHotelInformationById(Integer hotelid, HotelInformation hotelInformation) throws GlobalException;

	public List<HotelInformation> searchHotelNameOrLocation(String keyword);

	

	public List<HotelInformation> getHotelInfoByHotelName(String hotelname);

	public List<HotelInformation> getHotelInfoByHotelLocation(String hotellocation);

	public List<HotelInformation> getHotelByStatus(String hotelstatus);

	public HotelInformation updatehotelstatusById(Integer hotelid, String hotelstatus) throws GlobalException;

	public List<HotelInformation> getApprovedHotelStatus();

	public List<HotelInformation> getPendingHotelStatus();

	public List<HotelInformation> getDeniedHotels();

	public List<HotelInformation> retrieveAndDeniedHotels();

	public void deleteHotelIfStatusDenied(Integer hotelid) throws GlobalException;

	public HotelInformation getHotelInformationByHoteladminId(Integer hoteladminid)throws GlobalException;

	public boolean deleteHotelInformationById(Integer hotelId);

	



}
