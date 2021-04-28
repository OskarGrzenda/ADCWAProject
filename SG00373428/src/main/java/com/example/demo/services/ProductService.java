package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.Salesperson;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository pr;
	
	//Get one order in table by PID
	public Optional<Product> findByPid(String pid) throws SalespersonException
	{		
		Optional<Product> p = pr.findByPid(pid);
		
		return p;
	}
	
	//Get specific product by ORDERABLE //NOT BEING USED ATM	
	public List<Order> findByOrderable(String order)//, int qty
	{
		return pr.findAllByOrderable(order);
	}
}
