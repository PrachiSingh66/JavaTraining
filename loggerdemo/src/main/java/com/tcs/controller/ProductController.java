package com.tcs.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

@RestController
public class ProductController {
	Logger logger=(Logger) LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/home")
	public String home() {
		logger.info("This is info msg");
		logger.error("This is error msg");
		logger.debug("This is debg msg");
		logger.warn("This is warn msg");
		return "this is home";
	}
	@GetMapping("/aboutus")
	public String aboutus() {
		return "this is aboutus";
	}
}
