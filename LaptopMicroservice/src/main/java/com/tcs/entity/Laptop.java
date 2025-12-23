package com.tcs.entity;


import jakarta.persistence.*;


@Entity @Table(name = "laptops", uniqueConstraints = {
    @UniqueConstraint(name = "uk_laptop_serial", columnNames = {"serial_number"})
})
public class Laptop {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String brand;


  private String model;

 @Column(name="serial_number")
  private String serialNumber;

@Column(name="ram_gb")
  private Integer ramGb;

@Column(name="storage_gb")
  private Integer storageGb;

public Laptop() {
	super();
	// TODO Auto-generated constructor stub
}

public Laptop(String brand, String model, String serialNumber, Integer ramGb, Integer storageGb) {
	super();
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
