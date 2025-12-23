package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.tcs.entity.Laptop;
import com.tcs.service.LaptopService;

import java.util.List;


@RestController @RequestMapping("/api/laptops")
public class LaptopController {
	@Autowired
  private LaptopService service;

  @PostMapping
  public ResponseEntity<Laptop> create( @RequestBody Laptop laptop) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(laptop));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Laptop> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.get(id));
  }

  @GetMapping
  public ResponseEntity<List<Laptop>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Laptop> update(@PathVariable Long id,@RequestBody Laptop laptop) {
    return ResponseEntity.ok(service.update(id, laptop));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
