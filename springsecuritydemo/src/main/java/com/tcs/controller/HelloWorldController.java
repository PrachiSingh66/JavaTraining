package com.tcs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		String message="This is home page";
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	@GetMapping("/aboutus")
	public String aboutus() {
		return "This is about us page.";
	}
	
	@GetMapping("/addproduct")
	public String addProduct() {
		return "add product,accessed by only admin";
	}
	
	@GetMapping("/viewallproduct")
	public String viewallproduct() {
		return "access by both admin and user";
	}
}
