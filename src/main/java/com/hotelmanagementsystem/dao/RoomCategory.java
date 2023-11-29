package com.hotelmanagementsystem.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roomcategory")
public class RoomCategory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomcategoryid;
	
	@Column(length=40,nullable=false,unique=true)
	private String roomcategoryname;
	
	//ManyToMany Relation
	@JsonIgnore
	@OneToMany(mappedBy = "roomcategory")
	private List<RoomInformation> roominformation;
	
	public RoomCategory() {
		super();
	}
	
	public RoomCategory(String roomcategoryname) {
		super();
		this.roomcategoryname = roomcategoryname;
	}
	public Integer getRoomcategoryid() {
		return roomcategoryid;
	}
	public void setRoomcategoryid(Integer roomcategoryid) {
		this.roomcategoryid = roomcategoryid;
	}
	public String getRoomcategoryname() {
		return roomcategoryname;
	}
	public void setRoomcategoryname(String roomcategoryname) {
		this.roomcategoryname = roomcategoryname;
	}
	
	public List<RoomInformation> getRoominformation() {
		return roominformation;
	}
	public void setRoominformation(List<RoomInformation> roominformation) {
		this.roominformation = roominformation;
	}
	
	@Override
	public String toString() {
		return "RoomCategory [roomcategoryid=" + roomcategoryid + ", roomcategoryname=" + roomcategoryname + "]";
	}
	
}
