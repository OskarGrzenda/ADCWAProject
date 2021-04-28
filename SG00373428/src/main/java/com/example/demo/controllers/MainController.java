package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Salesperson;
import com.example.demo.models.validations.SalespersonPOSTValidations;
import com.example.demo.models.validations.SalespersonPUTValidations;
import com.example.demo.services.SalespersonService;
import com.fasterxml.jackson.annotation.JsonBackReference;

@RestController
@Validated
public class MainController {
	
	@Autowired
	SalespersonService ss;
	
	//Get All people in table
	@GetMapping(path="/api/salespeople")
	//@CrossOrigin(origins = "http://localhost:4200")
	public Iterable<Salesperson> getSalesPeople() 
	{
		return ss.getAllSalesPeople();
	}
	
	//Get one Salesperson by SPID
	@GetMapping(path="/api/salespeople/{spid}")
	public Optional<Salesperson> getSalesperson(@PathVariable String spid) throws SalespersonException
	{		
		Optional<Salesperson> sp = ss.findBySpid(spid);

		if(sp.isPresent())
		{
			return sp;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Salesperson: " + spid + " not found");
		}
	}
	
	//Delete Salesperson by SPID
	@DeleteMapping(path="/api/salespeople/{spid}")
	public void delSalesperson(@PathVariable String spid) throws SalespersonException 
	{
		Optional<Salesperson> sp = ss.findBySpid(spid);
		
		if(sp.isPresent())
		{
			ss.delete(sp.get());
		}
	}
	
	//Create New Salesperson
	@Validated(SalespersonPOSTValidations.class)
	@PostMapping(path="/api/salespeople", consumes = "application/json")
	public void newSalesperson(@Valid @RequestBody Salesperson sp)
	{
		ss.save(sp);
	}
	
	//Update Salesperson
	@Validated(SalespersonPUTValidations.class)
	@PutMapping(path="/api/salespeople/{spid}")
	public void updateSalesperson(@PathVariable String spid, @Valid @RequestBody Salesperson sp)
	{
		try 
		{
			ss.updateSalesperson(spid, sp);
		} 
		catch (SalespersonException e) 
		{
			e.printStackTrace();
		}
	}
	
}
