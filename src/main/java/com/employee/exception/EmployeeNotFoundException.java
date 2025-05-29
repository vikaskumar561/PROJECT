package com.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String msg;

	public EmployeeNotFoundException(String msg) {
		super(msg);

	}

}
