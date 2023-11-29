package com.hotelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.RoomInformation;

@Repository
public interface RoomInformationRepository extends JpaRepository<RoomInformation, Integer>{

	@Query(value = "select * from roominformation where hotelid=?1 and is_Booked is null", nativeQuery = true)
	List<RoomInformation> findRoomInformationByHotelId(Integer hotelid);

	@Query(value = "select * from roominformation where hoteladminid=?1", nativeQuery = true)
	List<RoomInformation> findRoomInformationByHotelAdminId(Integer hoteladminid);
	
	@Query(value = "select * from roominformation where roomid=?1", nativeQuery = true)
	RoomInformation findByRoomId(Integer roomid);

	@Query(value = "Select * from HotelManagementSystem.roominformation where roomnumber=?1 and clone_hotel_admin_id=?2", nativeQuery = true)
	RoomInformation findByRoomNumberWithAdminId(Integer roomnumber, Integer cloneHotelAdminId);

	@Query(value = "Select * from HotelManagementSystem.roominformation where hotelid=?1 and roomcategoryid=?2  and is_booked is null", nativeQuery = true)
	List<RoomInformation> findAllRoomInformationByHotelIdAndCategoryId(Integer hotelid, Integer categoryid);
}