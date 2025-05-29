package com.employee.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?>handleEmployeeException(EmployeeNotFoundException ex, WebRequest request)
	{
		
		errorResponse error=new errorResponse
				("Internal Server error",LocalDateTime.now(),ex.getMessage()
						);
		
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
