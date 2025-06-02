package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
