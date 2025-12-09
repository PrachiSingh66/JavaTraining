package com.tcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.tcs.entity.Employee;
import com.tcs.exception.IdException;
import com.tcs.repository.EmployeeRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	EmployeeRepository employeeRepository;
		

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println(" Database connected Successfully");
	}
	public void run(String... args) throws Exception{
		//Employee e=new Employee(2,"ankit",90600,"dev",23);
		//employeeRepository.save(e);
		//System.out.println("Inserted");
		
		List<Employee> employees=employeeRepository.findAll();
		employees.forEach(System.out::println);
		try {
			if(!employees.contains(1)) {
				throw new IdException("no id found");
			}
			else {
			Optional<Employee> emp=employeeRepository.findById(1);
			
			System.out.println(emp);}
		}
		catch(IdException e){
			System.out.println(e.getMessage());
		}
		//Employee emp=employeeRepository.findById(1).orElseThrow(()->new ArithmeticException("no id found"));
		//System.out.println(emp);
	}

}
