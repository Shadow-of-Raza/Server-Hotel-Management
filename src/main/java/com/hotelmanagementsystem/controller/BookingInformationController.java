package com.hotelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.dao.BookingInformation;
import com.hotelmanagementsystem.error.GlobalException;
import com.hotelmanagementsystem.service.BookingInformationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookingInformationController
{
	@Autowired
	private BookingInformationService bookingInformationService;
	
	//add booking information
	@PostMapping("/addBookingInformation")
	public BookingInformation addBookingInformation(@RequestBody BookingInformation bookingInformation)
	{
		return bookingInformationService.addBookingInformation(bookingInformation);
		
	}
	 //set customer,hotel and room id
	@PutMapping("/setCustomeridHotelidAndroomid/{bookingid}/{customerid}/{hotelid}/{roomid}")
	public BookingInformation setHotelAdminidAndHotelid(@PathVariable Integer bookingid,@PathVariable Integer customerid,@PathVariable Integer hotelid, @PathVariable Integer roomid ) throws GlobalException
	{
		return bookingInformationService.setHotelAdminidAndHotelid(bookingid,customerid,hotelid,roomid);
		
	}
	
	//get all booking information id
	@GetMapping("/getAllBookingInformation")
	public List<BookingInformation> getAllBookingInformation(){
		return bookingInformationService.getAllBookingInformation();
	}
	
	//get booking information by id
	@GetMapping("/getBookingInformationById/{bookingid}")
	public BookingInformation getBookingInformationById(@PathVariable ("bookingid") Integer bookingid) throws GlobalException
	{
		return bookingInformationService.getBookingInformationById(bookingid);
		
	}
	
	//update booking information
	@PutMapping("/updateBookingInformationById/{bookingid}")
	BookingInformation updateBookingInformationById(@PathVariable ("bookingid") Integer bookingid, @RequestBody BookingInformation bookingInformation)
	{
		return bookingInformationService.updateBookingInformationById(bookingid,bookingInformation);
	}
	
	@DeleteMapping("/deleteBookingInformationById/{bookingid}")
	String deleteBookingInformationById(@PathVariable ("bookingid") Integer bookingid) throws GlobalException
	{
		bookingInformationService.deleteBookingInformationById(bookingid);
		return "Booking deleted";
	}
	
	@GetMapping("/getBookingInformationByPaymentastatus/{paymentstatus}")
	public List< BookingInformation> getBookingInformationByPaymentastatus(@PathVariable ("paymentstatus") String paymentstatus)
	{
		return bookingInformationService.getBookingInformationByPaymentastatus(paymentstatus);
	}
	
	@GetMapping("/getSuccessfulPaymentStatus")
	public List<BookingInformation> getSuccessfulPaymentStatus()
	{
		return bookingInformationService.getSuccessfulPaymentStatus();
	}
	
	@GetMapping("/getPendingPaymentStatus")
	public List<BookingInformation> getPendingPaymentStatus(){
		return bookingInformationService.getPendingPaymentStatus();
	}
	
	
	@GetMapping("/getAllBookingInformationByHotelId/{hotelid}")
	public List<BookingInformation> getAllBookingInformationByHotelId(@PathVariable("hotelid") Integer hotelid){
		List<BookingInformation> bi = bookingInformationService.getAllBookingInformationByHotelId(hotelid);
		return bi;
	}
	
	@GetMapping("/getAllActiveBookingInformationByHotelId/{hotelid}")
	public List<BookingInformation> getAllActiveBookingInformationByHotelId(@PathVariable("hotelid") Integer hotelid){
		List<BookingInformation> bi = bookingInformationService.getAllActiveBookingInformationByHotelId(hotelid);
		return bi;
	}
	
	@GetMapping("/getAllBookingInformationByCustomerId/{customerid}")
	public List<BookingInformation> getAllBookingInformationByCustomerId(@PathVariable("customerid") Integer customerid){
		List<BookingInformation> bi = bookingInformationService.getAllBookingInformationByCustomerId(customerid);
		return bi;
	}
	
	@PutMapping("/reUpdateActiveStatusByBookingId/{bookingid}")
	public boolean reUpdateActiveStatusByBookingId(@PathVariable ("bookingid") Integer bookingid) {
		return bookingInformationService.reUpdateActiveStatusByBookingId(bookingid);
	}
	
}
