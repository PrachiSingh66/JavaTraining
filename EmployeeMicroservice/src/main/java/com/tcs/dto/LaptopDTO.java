package com.tcs.dto;

public class LaptopDTO {
  private Long id;
  private String brand;
  private String model;
  private String serialNumber;
  private Integer ramGb;
  private Integer storageGb;
public LaptopDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public LaptopDTO(Long id, String brand, String model, String serialNumber, Integer ramGb, Integer storageGb) {
	super();
	this.id = id;
	this.brand = brand;
	this.model = model;
	this.serialNumber = serialNumber;
	this.ramGb = ramGb;
	this.storageGb = storageGb;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getSerialNumber() {
	return serialNumber;
}
public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
}
public Integer getRamGb() {
	return ramGb;
}
public void setRamGb(Integer ramGb) {
	this.ramGb = ramGb;
}
public Integer getStorageGb() {
	return storageGb;
}
public void setStorageGb(Integer storageGb) {
	this.storageGb = storageGb;
}
  
}
