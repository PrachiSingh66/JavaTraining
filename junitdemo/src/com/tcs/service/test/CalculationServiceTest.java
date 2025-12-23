package com.tcs.service.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tcs.service.CalculationService;

class CalculationServiceTest {
	CalculationService calculation=new CalculationService();
	@Test
	public void addtest() {
		assertEquals(5,calculation.add(3, 2));
	}
	/*@Test
	public void validtest() {
		assertTrue(true,calculation.validage(34))
	}*/

}
