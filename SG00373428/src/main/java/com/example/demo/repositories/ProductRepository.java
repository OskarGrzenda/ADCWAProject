package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.Salesperson;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	public Optional<Product> findByPid(String pid); 

	public List<Order> findAllByOrderable(String orderable);
}
