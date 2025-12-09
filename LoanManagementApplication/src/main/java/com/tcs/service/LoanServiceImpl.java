package com.tcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.entity.Loan;
import com.tcs.exception.DuplicateLoanApplicationException;
import com.tcs.exception.InvalidLoanAmountException;
import com.tcs.exception.LoanNotFoundException;
import com.tcs.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService{
	@Autowired
	LoanRepository loanRepository;
	
	private static final double MIN_AMOUNT=1.0;
	private static final double MAX_AMOUNT=5000000.0;

	@Override
	public Loan createLoan(Loan loans) {
		
		if(loans.getLoanAmount()<=0 || loans.getLoanAmount()>MAX_AMOUNT)
			throw new InvalidLoanAmountException("Loan Amount must be between 1 and 5000000.");
		if(loanRepository.existsByApplicantNameAndStatus(loans.getApplicantName(), "PENDING"))
			throw new DuplicateLoanApplicationException("Applicant already has a loan in PENDING status");
		Loan loan=new Loan(loans.getApplicantName(),loans.getLoanAmount(),"PENDING");
		return loanRepository.save(loan);
	}

	@Override
	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}

	@Override
	public Loan getLoanById(Long id) {
		return loanRepository.findById(id).orElseThrow(()->new LoanNotFoundException("Loan not found with id: "+id));
	}

	@Override
	public Loan updateLoanStatus(Long id, String status) {
		Loan loan=getLoanById(id);
		String normalized=(status==null)?"":status.trim().toUpperCase();
		if(!normalized.equals("APPROVED") && !normalized.equals("REJECTED"))
			throw new IllegalArgumentException("Status must be APPROVED or REJECTED.");
		loan.setStatus(normalized);
		return loanRepository.save(loan);
	}
	
	@Override
	public String deleteLoanRequestById(Long id) {
		
	   if(!loanRepository.existsById(id))
		    throw new LoanNotFoundException("No Id present to delete");
		 
	    loanRepository.deleteById(id);
		
		return "Loan with the id :"+ id +" is deleted";
	}


}
