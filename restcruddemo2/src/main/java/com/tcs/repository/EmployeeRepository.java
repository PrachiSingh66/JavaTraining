package com.tcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	Employee findByName(String name);
	List<Employee> findByDebt(String debt);
	
	//jpql query
	//@Query("SELECT FROM employee e WHERE e.debt=:debtName")
	//List<Employee> getEmployeeByDebt(@Param("debtName") String debt);
}
