package com.tcs.service;
import com.tcs.entity.Laptop;
import java.util.List;

public interface LaptopService {
  Laptop create(Laptop laptop);
  Laptop get(Long id);
  List<Laptop> getAll();
  Laptop update(Long id, Laptop laptop);
  void delete(Long id);
}

