package com.hotelmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelmanagementsystem.dao.HotelAdmin;
import com.hotelmanagementsystem.dao.HotelInformation;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.HotelAdminRepository;
import com.hotelmanagementsystem.repository.HotelInformationRepository;

import java.util.*;
@Service
public class HotelInformationServiceImpl implements HotelInformationService
{
	@Autowired
	private HotelInformationRepository hotelInformationRepository;
    
	@Autowired 
	private HotelAdminRepository hotelAdminRepository;
	@Override
	public HotelInformation addHotelInformation(HotelInformation hotelInformation) {
		
		return hotelInformationRepository.save(hotelInformation);
	}

	@Override
	public HotelInformation setHotelAdminidToHotelInformation(Integer hotelid, 
			Integer hoteladminid) throws GlobalException {
		Optional<HotelInformation> hi=hotelInformationRepository.findById(hotelid);
		if(!hi.isPresent()) {
			throw new GlobalException("Hotel not exist");
		}
		Optional <HotelAdmin> ha=hotelAdminRepository.findById(hoteladminid);
		if(!ha.isPresent()) {
			throw new GlobalException("Hotel Admin not exist");
		}
		HotelInformation hi1=hotelInformationRepository.findById(hotelid).get();
		HotelAdmin ha1=hotelAdminRepository.findById(hoteladminid).get();
		if(hi1!=null) {
			hi1.setHotelAdminidToHotelInformation(ha1);
		}
		return hotelInformationRepository.save(hi1);
		
	}

	@Override
	public List<HotelInformation> getAllHotelInformation() {
		return hotelInformationRepository.findAll();
	}

	@Override
	public HotelInformation getHotelInformationById(Integer hotelid) throws GlobalException {
       Optional<HotelInformation> hi=hotelInformationRepository.findById(hotelid);
       HotelInformation hi1=null;
       if(!hi.isPresent()) {
    	   throw new GlobalException("Hotel information with id "+hotelid+" not found");
       }
       hi1=hotelInformationRepository.findById(hotelid).get();
		return hi1;
	}
	
	@Override
	public HotelInformation getHotelInformationByHoteladminId(Integer hoteladminid) throws GlobalException {
		HotelInformation hi= hotelInformationRepository.findByHotelAdminId(hoteladminid);
	
		HotelInformation hi1=null;
		if(hi == null) {
			throw new GlobalException("Hotel Admin with id "+hoteladminid+" not found");
		}
		System.out.println(hoteladminid);
		
		hi1=hotelInformationRepository.findByHotelAdminId(hoteladminid);
		System.out.println(hi1);
		return hi1;	
}
	

	@Override
	public HotelInformation updateHotelInformationById(Integer hotelid, HotelInformation hotelInformation) throws GlobalException {
		Optional<HotelInformation> hi=hotelInformationRepository.findById(hotelid);
		HotelInformation hi1=null;
		if(hi.isPresent()) {
			hi1=hi.get();
			if(hotelInformation.getHotelname()!=null) {
				hi1.setHotelname(hotelInformation.getHotelname());
			}
			if(hotelInformation.getHotellocation()!=null) {
				hi1.setHotellocation(hotelInformation.getHotellocation());
			}
			if(hotelInformation.getHotelimageurl()!=null) {
				hi1.setHotelimageurl(hotelInformation.getHotelimageurl());
			}
			if(hotelInformation.getHoteldescription()!=null) {
				hi1.setHoteldescription(hotelInformation.getHoteldescription());
			}
		}else {
			throw new GlobalException("Hotel information with "+hotelid+" not found");
		}
		return hotelInformationRepository.save(hi1);
	}
		
	
	@Override
	public boolean deleteHotelInformationById(Integer hotelId) {
	    Optional<HotelInformation> hotelInformation = hotelInformationRepository.findById(hotelId);
	    if (hotelInformation.isPresent()) {
	    	hotelInformationRepository.deleteHotelInformationById(hotelId);
	    	return true;
	    }else {
	    	return false;
	    }
	}

	

	@Override
	public List<HotelInformation> searchHotelNameOrLocation(String keyword) {
		return hotelInformationRepository.searchByNameOrLocation(keyword);
	}

	@Override
	public List<HotelInformation> getHotelInfoByHotelName(String hotelname) {
		return hotelInformationRepository.findByHotelName(hotelname);
	}

	@Override
	public List<HotelInformation> getHotelInfoByHotelLocation(String hotellocation) {
		return hotelInformationRepository.findByHotelLocation(hotellocation);
	}

	@Override
	public List<HotelInformation> getHotelByStatus(String hotelstatus) {
		return hotelInformationRepository.findByHotelStatus(hotelstatus);
	}

	@Override
	public HotelInformation updatehotelstatusById(Integer hotelid,String hotelstatus) throws GlobalException {
		Optional<HotelInformation> hi=hotelInformationRepository.findById(hotelid);
		HotelInformation hi1=null;
		if(hi.isPresent()) {
			hi1=hi.get();
			hi1.setHotelstatus(hotelstatus);
		}else {
			throw new GlobalException("Hotel with id "+hotelid+" not found");
		}
		return hotelInformationRepository.save(hi1);
	}

	@Override
	public List<HotelInformation> getApprovedHotelStatus() {
		return hotelInformationRepository.findApprovedHotels();
	}

	@Override
	public List<HotelInformation> getPendingHotelStatus() {
		return hotelInformationRepository.findPendingHotels();
	}

	@Override
	public List<HotelInformation> getDeniedHotels() {
		return hotelInformationRepository.findDeniedHotels();
	}

	@Override
	public List<HotelInformation> retrieveAndDeniedHotels() {
		List<HotelInformation> deniedHotels=hotelInformationRepository.findDeniedHotels();
		hotelInformationRepository.deleteDeniedHotels();
		
		return deniedHotels;
	}

	@Override
	public void deleteHotelIfStatusDenied(Integer hotelid) throws GlobalException {
		Optional<HotelInformation> hi=hotelInformationRepository.findById(hotelid);
		if(hi.isPresent()) {
			HotelInformation hi1=hi.get();
			if("denied".equalsIgnoreCase(hi1.getHotelstatus()))
			{
				hotelInformationRepository.delete(hi1);
			}else {
				throw new GlobalException("Hotel status is not denied. cannot delete");
			}
		}else {
			throw new GlobalException("Hotel with id "+hotelid+" not found");
		}
	}

	

	



	
}
