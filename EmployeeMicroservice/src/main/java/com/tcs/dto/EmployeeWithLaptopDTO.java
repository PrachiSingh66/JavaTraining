package com.tcs.dto;

import com.tcs.entity.Employee;

import java.util.Objects;

/**
 * Simple DTO to return Employee along with Laptop data fetched via Feign.
 * No Lombok used; plain Java getters/setters/constructors.
 */
public class EmployeeWithLaptopDTO {

    private Employee employee;
    private LaptopDTO laptop;

    // No-args constructor (required by Jackson for serialization/deserialization)
    public EmployeeWithLaptopDTO() {
    }

    // All-args constructor for convenience
    public EmployeeWithLaptopDTO(Employee employee, LaptopDTO laptop) {
        this.employee = employee;
        this.laptop = laptop;
    }

    // Getters and Setters
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LaptopDTO getLaptop() {
        return laptop;
    }

    public void setLaptop(LaptopDTO laptop) {
        this.laptop = laptop;
    }

    // Optional: helpful for logging/debugging
    @Override
    public String toString() {
        return "EmployeeWithLaptopDTO{" +
                "employee=" + employee +
                ", laptop=" + laptop +
                '}';
    }

    // Optional: useful in tests and collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeWithLaptopDTO)) return false;
        EmployeeWithLaptopDTO that = (EmployeeWithLaptopDTO) o;
        return Objects.equals(employee, that.employee) &&
               Objects.equals(laptop, that.laptop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, laptop);
    }
}

