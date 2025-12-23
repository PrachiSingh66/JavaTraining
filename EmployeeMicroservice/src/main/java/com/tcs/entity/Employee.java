package com.tcs.entity;

import jakarta.persistence.*;

@Entity @Table(name="employees", uniqueConstraints = {
    @UniqueConstraint(name="uk_employee_email", columnNames = {"email"}),
    @UniqueConstraint(name="uk_employee_laptop", columnNames = {"laptop_id"})
})
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

   @Column(nullable=false)
  private String name;

  private String email;

  private String department;

  // Nullable initially; UNIQUE enforces one-to-one assignment
  @Column(name="laptop_id", unique=true)
  private Long laptopId; // can be null (multiple nulls allowed in MySQL unique index)

public Employee() {
	super();
	// TODO Auto-generated constructor stub
}

public Employee(String name, String email, String department, Long laptopId) {
	super();
	this.name = name;
	this.email = email;
	this.department = department;
	this.laptopId = laptopId;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public Long getLaptopId() {
	return laptopId;
}

public void setLaptopId(Long laptopId) {
	this.laptopId = laptopId;
}

}

