package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;
import com.example.demo.models.Salesperson;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	//Gets order by year and quantity greater than
	public List<Order> findByOrderDateStartingWithAndOrderQuantityGreaterThan(String orderDate, int orderQuantity);
}
