package com.code.service;

import java.util.List;

import com.code.dto.CreateOrderRequest;
import com.code.entity.Order;

public interface OrderService {

	public Order createOrder(CreateOrderRequest request) ;
	
	
	List<Order> getOrdersByCustomer(Long customerId);
}
