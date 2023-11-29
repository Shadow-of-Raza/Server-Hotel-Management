package com.hotelmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.dao.RoomService;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.repository.RoomServiceRepository;

@Service
public class RoomServiceImpl implements RoomServiceService
{
    @Autowired
    private RoomServiceRepository roomServiceRepository;
	@Override
	public RoomService addRoomService(RoomService roomService) {
		return roomServiceRepository.save(roomService);
	}
	
	
	@Override
	public List<RoomService> getAllRoomService() {
		return roomServiceRepository.findAll();
	}


	@Override
	public RoomService getRoomServiceById(Integer roomserviceid) throws GlobalException {
		Optional<RoomService> rs=roomServiceRepository.findById(roomserviceid);
		RoomService rs1=null;
		if(!rs.isPresent()) {
			throw new GlobalException("roomservice with is "+roomserviceid+" not found");
		}
		rs1=roomServiceRepository.findById(roomserviceid).get();
		return rs1;
	}


	@Override
	public void deleteRoomServiceById(Integer roomserviceid) throws GlobalException {
		Optional<RoomService> rs=roomServiceRepository.findById(roomserviceid);
		RoomService rs1=null;
		if(!rs.isPresent()) {
			throw new GlobalException("Room service with id "+roomserviceid+" not found");
		}
		roomServiceRepository.deleteById(roomserviceid);
	}


	
}
