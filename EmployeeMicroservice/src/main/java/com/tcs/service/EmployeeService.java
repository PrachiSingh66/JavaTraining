package com.tcs.service;


import com.tcs.dto.EmployeeWithLaptopDTO;
import com.tcs.entity.Employee;
import java.util.List;

public interface EmployeeService {
  Employee create(Employee employee);
  Employee get(Long id);
  List<Employee> getAll();
  Employee update(Long id, Employee employee);
  void delete(Long id);

  Employee assignLaptop(Long employeeId, Long laptopId);
  EmployeeWithLaptopDTO getWithLaptop(Long id);
}
