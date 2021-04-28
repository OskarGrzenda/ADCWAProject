package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.models.Salesperson;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository or;
	
	//Get All people in table
	public Iterable<Order> getAllOrders()
	{
		return or.findAll();
	}
	
	//Get specific order by Year and Qty
	public List<Order> findByYearAndQty(String year, int qty)//, int qty
	{
		return or.findByOrderDateStartingWithAndOrderQuantityGreaterThan(year, qty);
	}
	
	//create new order
	public void save(Order o)
	{
		or.save(o);
	}
}
