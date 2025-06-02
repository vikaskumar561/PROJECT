package com.code.dto;

import lombok.Data;

@Data
public class ProductOrderRequest {

	private Long productId;
	private int quantity;

}
