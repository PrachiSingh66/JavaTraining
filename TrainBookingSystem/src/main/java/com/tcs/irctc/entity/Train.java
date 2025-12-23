package com.tcs.irctc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="trains")
public class Train {
	@Id
	@Column(name="train_id")
	private Long trainId;
	private String source;
	private String destination;
	@Column(name="ac_fare")
	private Double acFare;
	@Column(name="non_ac_fare")
	private Double nonAcFare;
	@Column(name="sleeper_fare")
	private Double sleeperFare;
	@Column(name="available_seats")
	private Integer availableSeats=100;
	@Version
	private Long version;
	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Train(Long trainId, String source, String destination, Double acFare, Double nonAcFare, Double sleeperFare,
			Integer availableSeats) {
		super();
		this.trainId = trainId;
		this.source = source;
		this.destination = destination;
		this.acFare = acFare;
		this.nonAcFare = nonAcFare;
		this.sleeperFare = sleeperFare;
		this.availableSeats = availableSeats;
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
	public Double getAcFare() {
		return acFare;
	}
	public void setAcFare(Double acFare) {
		this.acFare = acFare;
	}
	public Double getNonAcFare() {
		return nonAcFare;
	}
	public void setNonAcFare(Double nonAcFare) {
		this.nonAcFare = nonAcFare;
	}
	public Double getSleeperFare() {
		return sleeperFare;
	}
	public void setSleeperFare(Double sleeperFare) {
		this.sleeperFare = sleeperFare;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	
}
