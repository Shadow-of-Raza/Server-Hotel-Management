package com.hotelmanagementsystem.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.HotelInformation;

@Repository
public interface HotelInformationRepository  extends JpaRepository<HotelInformation, Integer>{

	@Query(value = "SELECT * FROM hotelinformation WHERE (hotelname LIKE %:keyword% OR hotellocation LIKE %:keyword%) AND hotelstatus = 'approved'", nativeQuery = true)
	List<HotelInformation> searchByNameOrLocation(@Param("keyword") String keyword);
	
	@Query(value="select * from hotelinformation where hotelname=?",nativeQuery = true)
	List<HotelInformation> findByHotelName(String hotelname);

	@Query(value="select * from hotelinformation where hotellocation=?",nativeQuery = true)
	List<HotelInformation> findByHotelLocation(String hotellocation);

	@Query(value="select * from hotelinformation where hotelstatus=?",nativeQuery = true)
	List<HotelInformation> findByHotelStatus(String hotelstatus);

	@Query(value="select * from hotelinformation where hotelstatus='approved'",nativeQuery = true)
	List<HotelInformation> findApprovedHotels();

	@Query(value="select * from hotelinformation where hotelstatus='pending'",nativeQuery = true)
	List<HotelInformation> findPendingHotels();

	@Query(value="select * from hotelinformation where hotelstatus='denied'",nativeQuery = true)
	List<HotelInformation> findDeniedHotels();

	@Transactional
	@Modifying
	@Query(value="delete from hotelinformation where hotelstatus='denied'",nativeQuery = true)
	void deleteDeniedHotels();

	@Query(value="select * from hotelinformation where hoteladminid=?1", nativeQuery = true)
	public HotelInformation findByHotelAdminId(Integer hoteladminid);

	@Transactional
	@Modifying
	@Query(value="delete from hotelinformation where hotelId=?1",nativeQuery = true)
	public void deleteHotelInformationById(Integer hotelId);


}
