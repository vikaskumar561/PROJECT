package com.employee.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class errorResponse {

	private String message;
	private LocalDateTime localdatatime;
	private String errormsg;
}
