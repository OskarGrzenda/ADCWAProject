package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;
import com.example.demo.models.Salesperson;

public interface SalespersonRepository extends CrudRepository<Salesperson, Integer> {
	
	//select * from salesperson where spid = "1";
	public Optional<Salesperson> findBySpid(String spid); 
}
