package com.tcs.service;

import java.util.List;

import com.tcs.entity.Employee;
import com.tcs.exception.IdNotFoundException;
import com.tcs.exception.InvalidAgeException;

public interface EmployeeService {
	public Employee addEmployee(Employee employee) throws InvalidAgeException;
	public List<Employee> getAllEmployee();
	public Employee getEmployeeById(int id) throws IdNotFoundException;
	public String deleteEmployeeById(int id) throws IdNotFoundException;
	public Employee updateEmployeeById(int id,Employee employee) throws IdNotFoundException;
	public Employee findByName(String name);
	public List<Employee> findByDebt(String debt);
}
