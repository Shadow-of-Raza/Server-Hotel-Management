package com.hotelmanagementsystem.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

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

import com.hotelmanagementsystem.dao.HotelInformation;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.service.HotelInformationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController


public class HotelInformationController 
{
	@Autowired
	private HotelInformationService hotelInformationService;
	
	//add hotel information
	@PostMapping("/addHotelInformation")
	public HotelInformation  addHotelInformation(@RequestBody HotelInformation hotelInformation )
	{
		HotelInformation hm=hotelInformationService.addHotelInformation(hotelInformation);
		//return new ResponseEntity<HotelInformation>(hm,HttpStatus.CREATED);
		return hm;
	}
	
	
	//set hotel admin id to hotel information  http://localhost:8080/setHotelAdminidToHotelInformation/1/1 
	@PutMapping("/setHotelAdminidToHotelInformation/{hotelid}/{hoteladminid}")
	public HotelInformation setHotelAdminidToHotelInformation(@PathVariable ("hotelid") Integer hotelid,
	@PathVariable("hoteladminid") Integer hoteladminid) throws GlobalException
	{
		return hotelInformationService.setHotelAdminidToHotelInformation(hotelid,hoteladminid);
		
	}
	
	//get all hotel information /getAllHotelInformation
	@GetMapping("/getAllHotelInformation")
	public List<HotelInformation> getAllHotelInformation()
	{
		return hotelInformationService.getAllHotelInformation();
	}
	
	
	//get hotel information by hotel id http://localhost:8080/getHotelInformationById/2
	@GetMapping("/getHotelInformationById/{hotelid}")
	public HotelInformation getHotelInformationById(@PathVariable ("hotelid") Integer hotelid) throws GlobalException
	{
		return hotelInformationService.getHotelInformationById(hotelid);
	}
	
	@GetMapping("/getHotelInformationByHoteladminId/{hoteladminid}")
	public HotelInformation getHotelInformationByHoteladminId(@PathVariable ("hoteladminid") Integer hoteladminid) throws GlobalException
	{
		return hotelInformationService.getHotelInformationByHoteladminId(hoteladminid);
	}
	
	//update hotel information
	@PutMapping("/updateHotelInformationById/{hotelid}")
	HotelInformation updateHotelInformationById(@PathVariable ("hotelid") Integer hotelid,@RequestBody HotelInformation hotelInformation) throws GlobalException
	{
		return hotelInformationService.updateHotelInformationById(hotelid,hotelInformation);
	}
	
	//Delete hotel information
	@DeleteMapping("/deleteHotelInformationById/{hotelId}")
    public ResponseEntity<String> deleteHotelInformationById(@PathVariable Integer hotelId) throws GlobalException {
        boolean deleted = hotelInformationService.deleteHotelInformationById(hotelId);
        if (deleted) {
            return new ResponseEntity<>("Hotel with ID " + hotelId + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Hotel with ID " + hotelId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
	
	
	//get hotel by name
	@GetMapping("/getHotelInfoByHotelName/{hotelname}")
	public List<HotelInformation> getHotelInfoByHotelName(@PathVariable ("hotelname") String hotelname)
	{
		
		return hotelInformationService.getHotelInfoByHotelName(hotelname);
	}
	
	//get hotel by location
	@GetMapping("/getHotelInfoByHotelLocation/{hotellocation}")
	public List<HotelInformation> getHotelInfoByHotelLocation(@PathVariable ("hotellocation") String hotellocation)
	{
		return hotelInformationService.getHotelInfoByHotelLocation(hotellocation);
	}
	
	//get hotels by their status
	@GetMapping("/getHotelByStatus/{hotelstatus}")
	public List<HotelInformation> getHotelByStatus(@PathVariable ("hotelstatus") String hotelstatus)
	{
		return hotelInformationService.getHotelByStatus(hotelstatus);
		
	}
	//search hotelname or hotel location
	@GetMapping("/searchHotelNameOrLocation/{keyword}")
	public ResponseEntity<List<HotelInformation>> searchHotelNameOrLocation(@PathVariable String keyword)
	{
		List<HotelInformation> hi= hotelInformationService.searchHotelNameOrLocation(keyword);
		return ResponseEntity.ok(hi);
	}
	
	//for updating hotelstatus
	@PutMapping("/updatehotelstatusById/{hotelid}/{hotelstatus}")
	public HotelInformation updatehotelstatusById(@PathVariable ("hotelid") Integer hotelid,@PathVariable ("hotelstatus") String hotelstatus) throws GlobalException
	{
		return hotelInformationService.updatehotelstatusById(hotelid,hotelstatus);
	}
	
	//for getting approved hotels
	@GetMapping("/getApprovedHotelStatus")
	public List<HotelInformation> getApprovedHotelStatus(){
		return hotelInformationService.getApprovedHotelStatus();
	}
	
	//for getting pending hotels
	@GetMapping("/getPendingHotelStatus")
	public List<HotelInformation> getPendingHotelStatus(){
		return hotelInformationService.getPendingHotelStatus();
	}
	
	@PostMapping("/denied")
	public ResponseEntity<String> retrieveAndDeniedHotels(){
		List<HotelInformation> deniedHotels=hotelInformationService.retrieveAndDeniedHotels();
		return ResponseEntity.ok("Denied hotesl retrieved and deleted successfully");
	}
	
	@DeleteMapping("/deleteHotelIfStatusDenied/{hotelid}")
	public ResponseEntity<String> deleteHotelIfStatusDenied(@PathVariable Integer hotelid)
	{
		try {
			hotelInformationService.deleteHotelIfStatusDenied(hotelid);
			return ResponseEntity.ok("Hotel with Id "+hotelid+" deleted if status is denied");
		}catch(GlobalException e) {
	        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage())	;
	        }
	}
	
	
	}

