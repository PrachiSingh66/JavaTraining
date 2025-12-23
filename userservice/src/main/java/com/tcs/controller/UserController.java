package com.tcs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.User;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) {
		return new User(id,"safan","safan@abc,com");
	}
}
