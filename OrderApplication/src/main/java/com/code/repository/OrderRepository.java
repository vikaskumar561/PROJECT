package com.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	
    List<Order> findByCustomerId(Long customerId);

}
