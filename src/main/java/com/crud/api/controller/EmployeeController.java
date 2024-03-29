package com.crud.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.api.exception.ResourceNotFoundException;
import com.crud.api.model.Employee;

import com.crud.api.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	

	
	//get allEmployees
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	// create employee rest api
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// get employee by id rest api
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With id :" +id));
		return ResponseEntity.ok(employee);
		//return employeeRepository.findAllById(id);
	}
	
	//update employee api
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With id :" +id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updateEmployee =  employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	//delete employee rest api
	@DeleteMapping("/employee/{id}")
	public  ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With id :" +id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
	

}
