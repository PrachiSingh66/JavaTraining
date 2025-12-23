package com.tcs.service;

import java.util.List;

import com.tcs.dto.CabBookingDTO;

public interface BookingService {
	Integer bookCab(CabBookingDTO bookingDTO);
	List<CabBookingDTO> getBookingDetails(Long mobileNo);
	void cancelBooking(Integer bookingId);
}
