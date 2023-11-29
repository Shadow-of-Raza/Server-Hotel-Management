package com.hotelmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.dao.RoomService;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.service.RoomServiceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomServiceController 
{
    @Autowired
    private RoomServiceService roomServiceService;
    
    
    //add  room service http://localhost:8080/addRoomService
    @PostMapping("/addRoomService")
    public RoomService addRoomService(@RequestBody RoomService roomService)
    {
		return roomServiceService.addRoomService(roomService);
    	
    }
    
    //get all room service http://localhost:8080/getAllRoomService
    @GetMapping("/getAllRoomService")
    public List<RoomService> getAllRoomService(){
		return roomServiceService.getAllRoomService();
    	
    }
    
    //get room service by id http://localhost:8080/getRoomServiceById/1
    @GetMapping("/getRoomServiceById/{roomserviceid}")
    public RoomService getRoomServiceById(@PathVariable ("roomserviceid") Integer roomserviceid) throws GlobalException {
		return roomServiceService.getRoomServiceById(roomserviceid);
    	
    }
    
    //delete room service by id
    @DeleteMapping("/deleteRoomServiceById/{roomserviceid}")
    String deleteRoomServiceById(@PathVariable ("roomserviceid") Integer roomserviceid) throws GlobalException
    {
    	roomServiceService.deleteRoomServiceById(roomserviceid);
    	return "Room Service deleted";
    }
   
}
