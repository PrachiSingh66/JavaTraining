package com.tcs.client;


import com.tcs.dto.LaptopDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "laptop-service", url = "http://localhost:8001")
public interface LaptopClient {
  @GetMapping("/api/laptops/{id}")
  LaptopDTO getLaptopById(@PathVariable("id") Long id);
}

