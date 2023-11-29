package com.hotelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.dao.BookingInformation;

@Repository
public interface BookingInformationRepository extends JpaRepository<BookingInformation, Integer> {

	@Query(value="select * from bookinginformation where paymentstatus=?",nativeQuery = true)
	List<BookingInformation> findBookingInfoByPaymentStatus(String paymentstatus);

	@Query(value="select * from bookinginformation where paymentstatus='successful'",nativeQuery = true)
	List<BookingInformation> findSuccessfulPayment();
	
	@Query(value="select * from bookinginformation where paymentstatus='pending'",nativeQuery = true)
	List<BookingInformation> findPendingPayment();

	@Query(value= "select * from bookinginformation where hotelid=?1", nativeQuery = true)
	List<BookingInformation> findAllByHotelId(Integer hotelid);

	@Query(value= "select * from bookinginformation where customerid=?1", nativeQuery = true)
	List<BookingInformation> findAllByCustomerId(Integer customerid);

	@Query(value= "select * from bookinginformation where hotelid=?1 and is_Active is true", nativeQuery = true)
	List<BookingInformation> findAllActiveBookingByHotelId(Integer hotelid);

	@Query(value = "select * from bookinginformation where bookingid=?1", nativeQuery = true)
	BookingInformation findByBookingId(Integer bookingid);
}
