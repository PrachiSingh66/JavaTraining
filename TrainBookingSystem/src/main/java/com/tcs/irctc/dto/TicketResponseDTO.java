package com.tcs.irctc.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketResponseDTO {
	private Long id;
	private String passengerName;
	private String email;
	private Long trainId;
	private String source;
	private String destination;
	private String seatType;
	private String bookingType;
	private Double fare;
	private LocalDate travelDate;
	private String status;
	private Boolean isDeleted;
	private Boolean highFareTicket;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public TicketResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketResponseDTO(Long id, String passengerName, String email, Long trainId, String source,
			String destination, String seatType, String bookingType, Double fare, LocalDate travelDate, String status,
			Boolean isDeleted, Boolean highFareTicket, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.passengerName = passengerName;
		this.email = email;
		this.trainId = trainId;
		this.source = source;
		this.destination = destination;
		this.seatType = seatType;
		this.bookingType = bookingType;
		this.fare = fare;
		this.travelDate = travelDate;
		this.status = status;
		this.isDeleted = isDeleted;
		this.highFareTicket = highFareTicket;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getTrainId() {
		return trainId;
	}
	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Boolean getHighFareTicket() {
		return highFareTicket;
	}
	public void setHighFareTicket(Boolean highFareTicket) {
		this.highFareTicket = highFareTicket;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
