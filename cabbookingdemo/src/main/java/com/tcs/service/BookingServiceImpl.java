package com.tcs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.dto.CabBookingDTO;
import com.tcs.entity.CabBooking;
import com.tcs.entity.FareEstimation;
import com.tcs.exception.TcsCabException;
import com.tcs.repository.BookingRepository;
import com.tcs.repository.FareRepository;

@Service
public class BookingServiceImpl implements BookingService{
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private FareRepository fareRepository;
	@Override
	public Integer bookCab(CabBookingDTO bookingDTO) {
		//find fare by source & destination
		Optional<FareEstimation> fareOpt=fareRepository.findBySourceAndDestination(bookingDTO.getSource(),bookingDTO.getDestination());
		if(fareOpt.isEmpty())
			throw new TcsCabException("Fare not available for given route.");
		Float fare=fareOpt.get().getFare();
		//Mapping DTO to entity
		CabBooking entity=new CabBooking();
		entity.setSource(bookingDTO.getSource());
		entity.setDestination(bookingDTO.getDestination());
		entity.setFare(fare);
		entity.setTravelDate(bookingDTO.getTravelDate());
		entity.setStatus('B');
		
		CabBooking saved=bookingRepository.save(entity);
		return saved.getBookingId();
	}
	@Override
	public List<CabBookingDTO> getBookingDetails(Long mobileNo) {
		List<CabBooking> bookings=bookingRepository.findByUserMobile(mobileNo);
		return bookings.stream().map(b-> new CabBookingDTO(b.getBookingId(),b.getSource(),b.getDestination(),b.getFare(),b.getTravelDate(),b.getUserMobile(),b.getStatus())).collect(Collectors.toList());
		
	}
	@Override
	public void cancelBooking(Integer bookingId) {
		CabBooking booking=bookingRepository.findById(bookingId).orElseThrow(()-> new TcsCabException("Booking is not found: "+bookingId));
		booking.setStatus('C');
		bookingRepository.save(booking);
		
		
	}
	
}
