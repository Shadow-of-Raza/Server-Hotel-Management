package com.hotelmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

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

import com.hotelmanagementsystem.dao.RoomInformation;
import com.hotelmanagementsystem.dao.RoomSetupDTO;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.service.RoomInformationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomInformationController
{
	@Autowired
	private RoomInformationService roomInformationService;
	
	
	
	
	@PostMapping("/setupRoom")
	public ResponseEntity<RoomInformation> setupRoom(@RequestBody RoomSetupDTO roomSetupDTO) throws GlobalException {
	    RoomInformation roomInformation = roomSetupDTO.getRoomInformation();
	    Integer roomCategoryId = roomSetupDTO.getRoomCategoryId();
	    List<Integer> roomServiceIds = roomSetupDTO.getRoomServiceIds();

	   
	    
	    if(roomInformationService.checkRoomNumber(roomInformation.getRoomnumber(), roomInformation.getCloneHotelAdminId())) {
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roomInformation);
	    }else {
	    // Add room information
	    roomInformationService.addRoomInformation(roomInformation);

	    // Set category
	    roomInformationService.addCategoryToRoom(roomInformation.getRoomid(), roomCategoryId);

	    // Set services
	    roomInformationService.setServiceToRoom(roomInformation.getRoomid(), roomServiceIds);

	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(roomInformation);
	    
	    }
	}
	
	
	
	
	
	
	//add room information
	@PostMapping("/addRoomInformation")
	public RoomInformation addRoomInformation(@RequestBody RoomInformation roomInformation )
	{
		return roomInformationService .addRoomInformation(roomInformation);
		
	}
	
	//put hotel admin id and hotel id to room information
	@PutMapping("/setHotelAdminidAndHotelid/{roomid}/{hoteladminid}/{hotelid}")
	public RoomInformation setHotelAdminidAndHotelid(@PathVariable Integer roomid,@PathVariable Integer hoteladminid,@PathVariable Integer hotelid ) throws GlobalException
	{
		return roomInformationService.setHotelAdminidAndHotelid(roomid,hoteladminid,hotelid);
	}
	
	//put roomcategory to room
	@PutMapping("/addCategoryToRoom/{roomid}/{roomcategoryid}")
	public RoomInformation addCategoryToRoom(@PathVariable Integer roomid,@PathVariable Integer roomcategoryid) throws GlobalException
	{
		return roomInformationService.addCategoryToRoom(roomid,roomcategoryid);
	}
	
	//put roomservice to room
	@PutMapping("/addServiceToRoom/{roomid}/{roomserviceid}")// List<Integer>/4/5/6/7
	public RoomInformation addServiceToRoom(@PathVariable Integer roomid, @PathVariable Integer roomserviceid) throws GlobalException
	{
		return roomInformationService.addServiceToRoom(roomid,roomserviceid);
		
	}
	
	@PostMapping("/setServiceToRoom/{roomid}")
	public RoomInformation setServiceToRoom(@PathVariable Integer roomid,@RequestBody List<Integer> roomserviceid) throws GlobalException {
		return roomInformationService.setServiceToRoom(roomid,roomserviceid);
		
	}
	
	@PutMapping("/updateIsBookedStatusByRoomId/{roomid}")
	public boolean updateIsBookedStatusByRoomId(@PathVariable("roomid") Integer roomid) {
		return roomInformationService.updateIsBookedStatusByRoomId(roomid);
	}
	
	@PutMapping("/reUpdateIsBookedStatusByRoomId/{roomid}")
	public boolean reUpdateIsBookedStatusByRoomId(@PathVariable("roomid") Integer roomid) {
		return roomInformationService.reUpdateIsBookedStatusByRoomId(roomid);
	}
	
	//get all room information http://localhost:8080/getAllRoomInformation
   	@GetMapping("/getAllRoomInformation")
   	public List<RoomInformation> getAllRoomInformation()
   	{
		return roomInformationService.getAllRoomInformation();
   		
   	}
   	
   	@GetMapping("/getAllRoomInformationByHotelId/{hotelid}")
   	public List<RoomInformation> getAllRoomInformationByHotelId(@PathVariable ("hotelid") Integer hotelid)
   	{
   		return roomInformationService.getAllRoomInformationByHotelId(hotelid);
   	}
	
   	@GetMapping("/getAllRoomsInformationByHotelAmdinId/{hoteladminid}")
   	public List<RoomInformation> getAllRoomsInformationByHotelAmdinId(@PathVariable("hoteladminid") Integer hoteladminid)
   	{
   		return roomInformationService.getAllRoomsInformationByHotelAmdinId(hoteladminid);
   	}
   	
	//get room information by id   http://localhost:8080/getRoomInformationById/1
   	@GetMapping("/getRoomInformationById/{roomid}")
   	public RoomInformation getRoomInformationById(@PathVariable ("roomid") Integer roomid) throws GlobalException
   	{
		return roomInformationService.getRoomInformationById(roomid);
   
   	}
   	
   	//put mapping for update record of room information
   	@PutMapping("/updateRoomInformationById/{roomid}")
   	RoomInformation updateRoomInformationById(@PathVariable ("roomid") Integer roomid,@RequestBody RoomInformation roomInformation) throws GlobalException
   	{
		return roomInformationService.updateRoomInformationById(roomid,roomInformation);
   		
   	}
   	
   	//delete room information
   	@DeleteMapping("/deleteRoomInformationById/{roomid}")
   	String deleteRoomInformationById(@PathVariable ("roomid") Integer roomid) throws GlobalException
   	{
   		roomInformationService.deleteRoomInformationById(roomid);
   		return "Room Information deleted";
   	}
   	
   	@GetMapping("/getAllRoomInformationByHotelIdAndCategoryId/{hotelid}/{categoryid}")
   	public List<RoomInformation> getAllRoomInformationByHotelIdAndCategoryId(@PathVariable ("hotelid") Integer hotelid, @PathVariable("categoryid") Integer categoryid){
   		return roomInformationService.getAllRoomInformationByHotelIdAndCategoryId(hotelid, categoryid);
   	}
 }
