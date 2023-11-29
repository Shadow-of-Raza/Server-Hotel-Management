package com.hotelmanagementsystem.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roomservice")
public class RoomService 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomserviceid;
	
	@Column(length=40,nullable=false,unique=true)
	private String roomservicename;
	
	//ManyToMany Relation
	@JsonIgnore
	@ManyToMany(mappedBy = "roomservice")
	private List<RoomInformation> roominformation;
	
	public RoomService() {
		super();
	}
	
	public RoomService(String roomservicename) {
		super();
		this.roomservicename = roomservicename;
	}
	public Integer getRoomserviceid() {
		return roomserviceid;
	}
	public void setRoomserviceid(Integer roomserviceid) {
		this.roomserviceid = roomserviceid;
	}
	public String getRoomservicename() {
		return roomservicename;
	}
	public void setRoomservicename(String roomservicename) {
		this.roomservicename = roomservicename;
	}
	
	public List<RoomInformation> getRoominformation() {
		return roominformation;
	}
	public void setRoominformation(List<RoomInformation> roominformation) {
		this.roominformation = roominformation;
	}
	@Override
	public String toString() {
		return "RoomService [roomserviceid=" + roomserviceid + ", roomservicename=" + roomservicename + "]";
	}
	
}
