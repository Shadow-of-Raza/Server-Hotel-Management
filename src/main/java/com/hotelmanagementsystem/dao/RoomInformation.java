package com.hotelmanagementsystem.dao;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "roominformation")
public class RoomInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomid;
    
    @Column(nullable = false)
    private Integer roomnumber;

    @Column(nullable = false)
    private Double roompriceperday;

    @Column(length = 500, nullable = false)
    private String roomdescription;

    @Column(length = 400, unique = true, nullable = false)
    private String roomimageurl;
    
    @Column(length=50, nullable = false)
    private String numberofpeople;
    
    private Boolean isBooked; 
    
    private Integer cloneHotelAdminId;
    
    @ManyToOne
    @JoinColumn(name = "hoteladminid")
    private HotelAdmin hotelAdmin;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelid")
    private HotelInformation hotelInformation;

    // ManyToOne Relation with RoomCategory
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "roomcategoryid")
    private  RoomCategory roomcategory;

    // ManyToMany Relation with RoomService
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
			    name = "roominfo_roomservice", 
				joinColumns = {@JoinColumn(name = "roomid")},
				inverseJoinColumns = {@JoinColumn(name = "roomserviceid")}
			  )
    private List<RoomService> roomservice;

	public RoomInformation() {
		super();
	}
	
	public Integer getRoomid() {
		return roomid;
	}
	

	public RoomInformation(Integer roomnumber, Double roompriceperday, String roomdescription, String roomimageurl,
			String numberofpeople, Boolean isBooked, Integer cloneHotelAdminId) {
		super();
		this.roomnumber = roomnumber;
		this.roompriceperday = roompriceperday;
		this.roomdescription = roomdescription;
		this.roomimageurl = roomimageurl;
		this.numberofpeople = numberofpeople;
		this.isBooked = isBooked;
		this.cloneHotelAdminId = cloneHotelAdminId;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	public Integer getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(Integer roomnumber) {
		this.roomnumber = roomnumber;
	}
	public Double getRoompriceperday() {
		return roompriceperday;
	}
	public void setRoompriceperday(Double roompriceperday) {
		this.roompriceperday = roompriceperday;
	}
	public String getRoomdescription() {
		return roomdescription;
	}
	public void setRoomdescription(String roomdescription) {
		this.roomdescription = roomdescription;
	}
	public HotelInformation getHotelInformation() {
		return hotelInformation;
	}
	public void setHotelInformation(HotelInformation hotelInformation) {
		this.hotelInformation = hotelInformation;
	}
	public HotelAdmin getHotelAdmin() {
		return hotelAdmin;
	}
	public void setHotelAdmin(HotelAdmin hotelAdmin) {
		this.hotelAdmin = hotelAdmin;
	}
	
	public String getNumberofpeople() {
		return numberofpeople;
	}

	public void setNumberofpeople(String numberofpeople) {
		this.numberofpeople = numberofpeople;
	}

	public RoomCategory getRoomcategory() {
		return roomcategory;
	}
	public void setRoomcategory(RoomCategory roomcategory) {
		this.roomcategory = roomcategory;
	}
	public List<RoomService> getRoomservice() {
		return roomservice;
	}
	public void setRoomservice(List<RoomService> roomservice) {
		this.roomservice = roomservice;
	}
	
	public String getRoomimageurl() {
		return roomimageurl;
	}
	public void setRoomimageurl(String roomimageurl) {
		this.roomimageurl = roomimageurl;
	}
	
	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}


	public Integer getCloneHotelAdminId() {
		return cloneHotelAdminId;
	}

	public void setCloneHotelAdminId(Integer cloneHotelAdminId) {
		this.cloneHotelAdminId = cloneHotelAdminId;
	}

	

	

	@Override
	public String toString() {
		return "RoomInformation [roomid=" + roomid + ", roomnumber=" + roomnumber + ", roompriceperday="
				+ roompriceperday + ", roomdescription=" + roomdescription + ", roomimageurl=" + roomimageurl
				+ ", numberofpeople=" + numberofpeople + ", isBooked=" + isBooked + ", cloneHotelAdminId="
				+ cloneHotelAdminId + "]";
	}

	public void addCategoryToRoom(RoomCategory cr1) {
		this.roomcategory=cr1;		
	}

	
	public void addServiceToRoom(RoomService rs1) {
		   this.roomservice.add(rs1);
	}
	
	
}
