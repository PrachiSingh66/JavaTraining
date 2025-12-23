package com.tcs.service;

import com.tcs.client.LaptopClient;
import com.tcs.dto.EmployeeWithLaptopDTO;
import com.tcs.dto.LaptopDTO;
import com.tcs.entity.Employee;
import com.tcs.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
  private EmployeeRepository repo;
	@Autowired
  private LaptopClient laptopClient;

  @Override
  public Employee create(Employee employee) {
    employee.setLaptopId(null); // initially NULL per requirement
    return repo.save(employee);
  }

  @Override public Employee get(Long id) {
    return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));
  }

  @Override public List<Employee> getAll() { return repo.findAll(); }

  @Override
  public Employee update(Long id, Employee payload) {
    Employee e = get(id);
    e.setName(payload.getName());
    e.setEmail(payload.getEmail());
    e.setDepartment(payload.getDepartment());
    // DO NOT change laptopId here; use assignLaptop API
    return repo.save(e);
  }

  @Override public void delete(Long id) { repo.delete(get(id)); }

  /**
   * Assign a laptop ensuring: employee exists, laptop exists, laptop not already assigned.
   * Uses unique index to enforce one-to-one; returns 409 if conflict.
   */
  @Override @Transactional
  public Employee assignLaptop(Long employeeId, Long laptopId) {
    Employee e = get(employeeId);

    // Check laptop exists via Feign
    LaptopDTO laptop = laptopClient.getLaptopById(laptopId);
    if (laptop == null || laptop.getId() == null)
      throw new EntityNotFoundException("Laptop not found: " + laptopId);

    // Business rule: if already assigned, reject
    repo.findByLaptopId(laptopId).ifPresent(existing -> {
      if (!existing.getId().equals(employeeId))
        throw new DataIntegrityViolationException("Laptop already assigned to employeeId=" + existing.getId());
    });

    e.setLaptopId(laptopId);
    return repo.save(e);
  }

  @Override
  public EmployeeWithLaptopDTO getWithLaptop(Long id) {
    Employee e = get(id);
    LaptopDTO laptop = null;
    if (e.getLaptopId() != null) {
      laptop = laptopClient.getLaptopById(e.getLaptopId());
    }
    EmployeeWithLaptopDTO dto = new EmployeeWithLaptopDTO();
    dto.setEmployee(e);
    dto.setLaptop(laptop);
    return dto;
  }
}
