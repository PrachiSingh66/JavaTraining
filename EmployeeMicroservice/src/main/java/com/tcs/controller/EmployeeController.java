package com.tcs.controller;


import com.tcs.dto.EmployeeWithLaptopDTO;
import com.tcs.entity.Employee;
import com.tcs.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/employees") 
public class EmployeeController {
	@Autowired
  private EmployeeService service;

  // 1. Create Employee (laptopId NULL)
  @PostMapping
  public ResponseEntity<Employee> create(@RequestBody Employee employee) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
  }

  // 2. Assign Laptop to Employee
  @PutMapping("/{id}/assign-laptop/{laptopId}")
  public ResponseEntity<Employee> assignLaptop(@PathVariable Long id, @PathVariable Long laptopId) {
    return ResponseEntity.ok(service.assignLaptop(id, laptopId));
  }

  // 3. Get Employee details
  @GetMapping("/{id}")
  public ResponseEntity<Employee> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

  // 4. Get Employee + Laptop (via Feign)
  @GetMapping("/{id}/with-laptop")
  public ResponseEntity<EmployeeWithLaptopDTO> getWithLaptop(@PathVariable Long id) {
    return ResponseEntity.ok(service.getWithLaptop(id));
  }

  // 5. Update Employee details (excluding laptop)
  @PutMapping("/{id}")
  public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
    return ResponseEntity.ok(service.update(id, employee));
  }

  // 6. Delete Employee
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  // BONUS: List all
  @GetMapping
  public ResponseEntity<List<Employee>> all() { return ResponseEntity.ok(service.getAll()); }
}

