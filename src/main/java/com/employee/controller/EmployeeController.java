package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeResponseDto;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/get-employee-department/{id}")
	public ResponseEntity<?> getAllData(@PathVariable("id") long id) {

		log.info("Received request to fetch employee and department data for employee ID: {}", id);

		EmployeeResponseDto data = employeeService.getAllData(id);
		
		
		log.info("Sucesfullly fetched the employee and department data for employee ID: {}" ,id);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
