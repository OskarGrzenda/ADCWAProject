package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.Salesperson;
import com.example.demo.models.validations.OrderGETValidations;
import com.example.demo.models.validations.OrderPOSTValidations;
import com.example.demo.models.views.OrderViews;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.SalespersonService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@Validated
public class OrdersController {
	
	@Autowired
	OrderService os;
	
	@Autowired
	SalespersonService ss;
	
	@Autowired
	ProductService ps;

	//Get all orders, person linked to order and what product they ordered
	@GetMapping(path="/api/orders")
	public Iterable<Order> getOrders() 
	{
		return os.getAllOrders();
	}
	
	//Get by specific Year and Qty 
	@Validated(OrderGETValidations.class)
	@GetMapping(path="/api/specificOrders") 
	public List<Order> getSpecificOrders(@Valid @RequestParam("year") String year, @RequestParam("qty") int qty)
	{
		System.out.println(year);
		System.out.println(qty);
		return os.findByYearAndQty(year, qty);
	}
	
	//create new order
	@Validated(OrderPOSTValidations.class)
	@PostMapping(path="/api/orders", consumes = "application/json")
	public void newOrder(@Valid @RequestBody Order o) throws SalespersonException
	{
		//check if pid exists
		String pid = o.getorderProduct().getPid();
		Optional<Product> p = ps.findByPid(pid);
		
		//Checking if SPID exists.
		String spid = o.getOrderSalesperson().getSpid();
		Optional<Salesperson> sp = ss.findBySpid(spid);
	
		if(sp.isPresent())
		{
			o.setOrderSalesperson(sp.get());
			o.setOrderProduct(p.get());
			os.save(o);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Salesperson " + spid + " does not exist.");
		}
		
		//Checking if PID exists.
		if(p.isPresent())
		{
			o.setOrderSalesperson(sp.get());
			o.setOrderProduct(p.get());
			os.save(o);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product " + pid + " does not exist.");
		}
		
		//Checking if product is ORDERABLE.
		String order = p.get().getOrderable();
		
		if(order.equals("true"))
		{
			o.setOrderSalesperson(sp.get());
			o.setOrderProduct(p.get());
			os.save(o);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product " + pid + " is not orderable.");
		}
		
		//Checking if product orderQuantity is less than stock on hand.
		int quantity = p.get().getQuantity();
		int orderQuantity = o.getOrderQuantity();
		System.out.println(quantity);
		System.out.println(orderQuantity);
		
		if(quantity > orderQuantity)
		{
			o.setOrderSalesperson(sp.get());
			o.setOrderProduct(p.get());
			p.get().setQuantity(p.get().getQuantity() - o.getOrderQuantity());
			os.save(o);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock on hand: " + quantity + ", less than order quantity: " + orderQuantity);
		}
	}
	
}
