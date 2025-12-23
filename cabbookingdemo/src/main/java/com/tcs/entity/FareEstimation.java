package com.tcs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FareEstimation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int fareId;
	private String source;
	private String destination;
	private Float fare;
	public FareEstimation(String source, String destination, Float fare) {
		super();
		this.source = source;
		this.destination = destination;
		this.fare = fare;
	}
	public int getFareId() {
		return fareId;
	}
	public void setFareId(int fareId) {
		this.fareId = fareId;
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
	
}
