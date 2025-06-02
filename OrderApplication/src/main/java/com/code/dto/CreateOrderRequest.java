package com.code.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateOrderRequest {

	private Long customerId;
	private List<ProductOrderRequest> items;
}
