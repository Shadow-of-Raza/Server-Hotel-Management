package com.hotelmanagementsystem.dao;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "hotelinformation")
public class HotelInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelid;

    @Column(length = 50, nullable = false)
    private String hotelname;

    @Column(length = 35, nullable = false)
    private String hotellocation;

    @Column(length = 10, nullable = false)
    private String hotelstatus;

    @Column(length = 255, nullable = false)
    private String hoteldescription;

    @Column(length = 150, unique = true)
    private String hotelimageurl;

    @JsonIgnore
    // OneToOne Relation with HotelAdmin
    @OneToOne
    @JoinColumn(name = "hoteladminid")
    private HotelAdmin hoteladmin;

    // OneToMany Relation with RoomInformation
    @OneToMany(mappedBy = "hotelInformation", cascade = CascadeType.ALL)
    private List<RoomInformation> roominformation;

	 
	public HotelInformation() {
		super();
	}
	
	public HotelInformation(String hotelname, String hotellocation, String hotelstatus,String hoteldescription, String hotelimageurl) {
		super();
		this.hotelname = hotelname;
		this.hotellocation = hotellocation;
		this.hotelstatus = hotelstatus;
		this.hoteldescription=hoteldescription;
		this.hotelimageurl=hotelimageurl;
	}
	
	public Integer getHotelid() {
		return hotelid;
	}
	public void setHotelid(Integer hotelid) {
		this.hotelid = hotelid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getHotellocation() {
		return hotellocation;
	}
	public void setHotellocation(String hotellocation) {
		this.hotellocation = hotellocation;
	}
	public String getHotelstatus() {
		return hotelstatus;
	}
	public void setHotelstatus(String hotelstatus) {
		this.hotelstatus = hotelstatus;
	}
	
	public HotelAdmin getHoteladmin() {
		return hoteladmin;
	}
	public void setHoteladmin(HotelAdmin hoteladmin) {
		this.hoteladmin = hoteladmin;
	}
	
	public List<RoomInformation> getRoominformation() {
		return roominformation;
	}
	public void setRoominformation(List<RoomInformation> roominformation) {
		this.roominformation = roominformation;
	}
	
	public String getHoteldescription() {
		return hoteldescription;
	}

	public void setHoteldescription(String hoteldescription) {
		this.hoteldescription = hoteldescription;
	}

	public String getHotelimageurl() {
		return hotelimageurl;
	}
	public void setHotelimageurl(String hotelimageurl) {
		this.hotelimageurl = hotelimageurl;
	}
	
	@Override
	public String toString() {
		return "HotelInformation [hotelid=" + hotelid + ", hotelname=" + hotelname + ", hotellocation=" + hotellocation
				+ ", hotelstatus=" + hotelstatus + ", hotelimageurl=" + hotelimageurl + "]";
	}

	public void setHotelAdminidToHotelInformation(HotelAdmin ha1) {
        this.hoteladmin = ha1;
    }
     
     
}
