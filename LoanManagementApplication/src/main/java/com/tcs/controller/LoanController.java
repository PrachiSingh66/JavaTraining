package com.tcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.Loan;
import com.tcs.service.LoanService;

@RestController
@RequestMapping
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	public static class UpdateLoanStatusRequest{
		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
	@PostMapping("/loans")
	public Loan createLoan(@RequestBody Loan loans) {
		return loanService.createLoan(loans);
	}
	@GetMapping("/loans")
	public List<Loan> getAllLoans(){
		return loanService.getAllLoans();
	}
	
	@GetMapping("/loans/{id}")
	public Loan getLoanById(@PathVariable Long id) {
		return loanService.getLoanById(id);
	}
	@PutMapping("/loans/{id}/status")
	public Loan updateStatus(@PathVariable Long id, @RequestBody UpdateLoanStatusRequest request) {
		Loan updated=loanService.updateLoanStatus(id,request.getStatus());
		return updated;
	}
	@DeleteMapping("/loans/{id}")
	public String deleteLoanRequestById(@PathVariable Long id)
	{
		return loanService.deleteLoanRequestById(id);
	}
	
}
