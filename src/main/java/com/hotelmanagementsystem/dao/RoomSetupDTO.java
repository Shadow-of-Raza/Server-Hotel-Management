package com.hotelmanagementsystem.dao;

import java.util.List;

public class RoomSetupDTO {
	private RoomInformation roomInformation;
    private Integer roomCategoryId;
    private List<Integer> roomServiceIds;
    
    
    
	public RoomSetupDTO(RoomInformation roomInformation, Integer roomCategoryId, List<Integer> roomServiceIds) {
		super();
		this.roomInformation = roomInformation;
		this.roomCategoryId = roomCategoryId;
		this.roomServiceIds = roomServiceIds;
	}
	public RoomSetupDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomInformation getRoomInformation() {
		return roomInformation;
	}
	public void setRoomInformation(RoomInformation roomInformation) {
		this.roomInformation = roomInformation;
	}
	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public List<Integer> getRoomServiceIds() {
		return roomServiceIds;
	}
	public void setRoomServiceIds(List<Integer> roomServiceIds) {
		this.roomServiceIds = roomServiceIds;
	}
	@Override
	public String toString() {
		return "RoomSetupDTO [roomInformation=" + roomInformation + ", roomCategoryId=" + roomCategoryId
				+ ", roomServiceIds=" + roomServiceIds + "]";
	}
    
    
}