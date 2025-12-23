package com.tcs.dto;

import java.time.LocalDate;

public class CabBookingDTO {
		private int bookingId;
		private String source;
		private String destination;
		private Float fare;
		private LocalDate travelDate;
		private Long userMobile;
		private Character status;
		public int getBookingId() {
			return bookingId;
		}
		
		public CabBookingDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CabBookingDTO(int bookingId, String source,String destination, Float fare, LocalDate travelDate, Long userMobile,
				Character status) {
			super();
			this.bookingId = bookingId;
			this.source = source;
			this.destination=destination;
			this.fare = fare;
			this.travelDate = travelDate;
			this.userMobile = userMobile;
			this.status = status;
		}

		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
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

		public Float getFare() {
			return fare;
		}
		public void setFare(Float fare) {
			this.fare = fare;
		}
		public LocalDate getTravelDate() {
			return travelDate;
		}
		public void setTravelDate(LocalDate travelDate) {
			this.travelDate = travelDate;
		}
		public Long getUserMobile() {
			return userMobile;
		}
		public void setUserMobile(Long userMobile) {
			this.userMobile = userMobile;
		}
		public Character getStatus() {
			return status;
		}
		public void setStatus(Character status) {
			this.status = status;
		}
		
}
