package com.code.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderItems {

	
	
	   @Id @GeneratedValue
	    private Long id;

	    @ManyToOne
	    private Product product;

	    @ManyToOne
	    private Order order;

	    private int quantity;
}
