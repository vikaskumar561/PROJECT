package com.code.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dto.CreateOrderRequest;
import com.code.dto.ProductOrderRequest;
import com.code.entity.Customer;
import com.code.entity.Order;
import com.code.entity.OrderItems;
import com.code.entity.Product;
import com.code.repository.CustomerRepository;
import com.code.repository.OrderRepository;
import com.code.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository  productRepository ;
	
	@Override
	public Order createOrder(CreateOrderRequest request) {

		// Get the customer who is placing the order
		Customer customer = customerRepository.findById(request.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		// Create a new order and link the customer
		Order order = new Order();
		order.setCustomer(customer);
		order.setCreatedAt(LocalDateTime.now());

		// Convert each item in the request to an OrderItem
		List<OrderItems> orderItems = new ArrayList<>();

		for (ProductOrderRequest itemRequest : request.getItems()) {
			Product product = productRepository.findById(itemRequest.getProductId())
					.orElseThrow(() -> new RuntimeException("Product not found"));

			OrderItems item = new OrderItems();
			item.setProduct(product);
			item.setQuantity(itemRequest.getQuantity());
			item.setOrder(order);

			orderItems.add(item);
		}

		// Set the items in the order
		order.setItems(orderItems);

		// Save the full order
		return orderRepository.save(order);
	}

	
	@Override
	public List<Order> getOrdersByCustomer(Long customerId) {
		return orderRepository.findByCustomerId(customerId);

	}}
