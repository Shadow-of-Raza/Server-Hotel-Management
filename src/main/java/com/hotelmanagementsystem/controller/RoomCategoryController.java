package com.hotelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.dao.RoomCategory;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.service.RoomCategoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomCategoryController 
{
	@Autowired
	private RoomCategoryService roomCategoryService;
	
	//add roomcategory
	@PostMapping("/addRoomCategory")
	public RoomCategory addRoomCategory(@RequestBody RoomCategory roomCategory) 
	{
		return roomCategoryService.addRoomCategory(roomCategory);
		
	}
	
	//get all room category http://localhost:8080/getAllRoomCategory
	@GetMapping("/getAllRoomCategory")
	public List<RoomCategory>getAllRoomCategory()
	{
		return roomCategoryService.getAllRoomCategory() ;
	}
	
	//get room category by id http://localhost:8080/getRoomCategoryById/2
	@GetMapping("/getRoomCategoryById/{roomcategoryid}")
	public RoomCategory getRoomCategoryById(@PathVariable ("roomcategoryid") Integer roomcategoryid) throws GlobalException
	{
		return roomCategoryService.getRoomCategoryById(roomcategoryid);
		
	}
	
	@DeleteMapping("/deleteRoomCategoryById/{roomcategoryid}")
	String deleteRoomCategoryById(@PathVariable ("roomcategoryid") Integer roomcategoryid) throws GlobalException
	{
		roomCategoryService.deleteRoomCategoryById(roomcategoryid);
		return "Room Category deleted";
	}
	
	
}
