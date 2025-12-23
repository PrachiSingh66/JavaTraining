package com.tcs.service;


import com.tcs.entity.Laptop;
import com.tcs.repository.LaptopRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {
	@Autowired
	private LaptopRepository repo;

  @Override
  public Laptop create(Laptop laptop) {
    if (repo.existsBySerialNumber(laptop.getSerialNumber()))
      throw new DataIntegrityViolationException("Duplicate serial number");
    return repo.save(laptop);
  }

  @Override public Laptop get(Long id) {
    return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Laptop not found: " + id));
  }

  @Override public List<Laptop> getAll() { return repo.findAll(); }

  @Override
  public Laptop update(Long id, Laptop laptop) {
    Laptop existing = get(id);
    existing.setBrand(laptop.getBrand());
    existing.setModel(laptop.getModel());
    existing.setSerialNumber(laptop.getSerialNumber());
    existing.setRamGb(laptop.getRamGb());
    existing.setStorageGb(laptop.getStorageGb());
    return repo.save(existing);
  }

  @Override public void delete(Long id) { repo.delete(get(id)); }
}

