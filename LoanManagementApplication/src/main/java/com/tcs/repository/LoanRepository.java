package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
	boolean existsByApplicantNameAndStatus(String applicantName,String status);
}
