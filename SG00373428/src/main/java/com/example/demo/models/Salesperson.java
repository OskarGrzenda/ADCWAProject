package com.example.demo.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.example.demo.models.validations.SalespersonPOSTValidations;
import com.example.demo.models.validations.SalespersonPUTValidations;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Salesperson {
	@JsonBackReference
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	@NotNull(message = "spid must be provided", groups = SalespersonPOSTValidations.class)
	@Null(message = "spid must not be provided", groups = SalespersonPUTValidations.class)
	private String spid;
	@NotNull(message = "name must be provided", groups = SalespersonPOSTValidations.class)
	@NotNull(message = "name must be provided", groups = SalespersonPUTValidations.class)
	private String name;
	
	@OneToMany(mappedBy = "orderSalesperson")
	@JsonIgnore
	private List<Order> salespersonOrders;
	
	public Salesperson() 
	{
		super();
	}
	
	public Salesperson(int id, String spid, String name, List<Order> salespersonOrders) {
		super();
		this.id = id;
		this.spid = spid;
		this.name = name;
		this.salespersonOrders = salespersonOrders;
	}

	public Salesperson(String name) 
	{
		super();
		this.name = name;
	}
	
	public String getSpid() 
	{
		return spid;
	}
	
	public void setSpid(String spid) 
	{
		this.spid = spid;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public List<Order> getSalespersonOrders() 
	{
		return salespersonOrders;
	}
	
	public void SalespersonOrders(List<Order> salespersonOrders) 
	{
		this.salespersonOrders = salespersonOrders;
	}

	public void setSalesperson(Salesperson salesperson) {
		this.name = name;

	}
}
