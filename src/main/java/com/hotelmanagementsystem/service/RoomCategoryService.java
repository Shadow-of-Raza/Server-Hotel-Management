package com.hotelmanagementsystem.service;

import java.util.List;

import com.hotelmanagementsystem.dao.RoomCategory;
import com.hotelmanagementsystem.error.GlobalException;

public interface RoomCategoryService {

	public RoomCategory addRoomCategory(RoomCategory roomCategory);

	public List<RoomCategory> getAllRoomCategory();

	public RoomCategory getRoomCategoryById(Integer roomcategoryid) throws GlobalException;

	public void deleteRoomCategoryById(Integer roomcategoryid) throws GlobalException;

}
