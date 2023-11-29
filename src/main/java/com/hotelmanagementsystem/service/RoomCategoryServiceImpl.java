package com.hotelmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.dao.RoomCategory;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.RoomCategoryRepository;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService
{
	@Autowired
	private  RoomCategoryRepository roomCategoryRepository;
	
	@Override
	public RoomCategory addRoomCategory(RoomCategory roomCategory) {
		return roomCategoryRepository.save(roomCategory);
	}

	@Override
	public List<RoomCategory> getAllRoomCategory() {
		return roomCategoryRepository.findAll();
	}

	@Override
	public RoomCategory getRoomCategoryById(Integer roomcategoryid) throws GlobalException {
		Optional<RoomCategory> rc=roomCategoryRepository.findById(roomcategoryid);
		RoomCategory rc1=null;
		if(!rc.isPresent()) {
			throw new GlobalException("Room category with id "+roomcategoryid+" not found");
		}
		rc1=roomCategoryRepository.findById(roomcategoryid).get();
		return rc1;
	}

	@Override
	public void deleteRoomCategoryById(Integer roomcategoryid) throws GlobalException {
		Optional<RoomCategory> rc=roomCategoryRepository.findById(roomcategoryid);
		RoomCategory rc1=null;
		if(!rc.isPresent()) {
			throw new GlobalException("Room category with id "+roomcategoryid+" not found");
		}
		roomCategoryRepository.deleteById(roomcategoryid);
	}

}
