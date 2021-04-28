package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Salesperson;
import com.example.demo.repositories.SalespersonRepository;

@Service
public class SalespersonService {
	
	@Autowired
	SalespersonRepository sr;
	
	//Get All people in table
	public Iterable<Salesperson> getAllSalesPeople()
	{
		return sr.findAll();
	}
	
	//Get one person in table by SPID
	public Optional<Salesperson> findBySpid(String spid) throws SalespersonException
	{		
		Optional<Salesperson> sp = sr.findBySpid(spid);
		
		return sp;
	}
	
	//Delete Salesperson
	public void delete(Salesperson sp) throws SalespersonException
	{
		try
		{
			sr.delete(sp);
		}
		catch (DataIntegrityViolationException e) 
		{
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Salesperson with SPID: " + sp.getSpid() + " can't be deleted. He/She has orders.");
		}
	}
	
	//Create New Salesperson
	public void save(Salesperson sp)
	{		
		try
		{
			sr.save(sp);
		}
		catch (DataIntegrityViolationException e) 
		{
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Salesperson with SPID: " + sp.getSpid() + " already exists.");
		}
	}
	
	//Update Salesperson
	public Optional<Salesperson> updateSalesperson(String spid, Salesperson sp) throws SalespersonException
	{
		Optional<Salesperson> salesperson = sr.findBySpid(spid);
		
		if(salesperson.isPresent())
		{
			salesperson.get().setName(sp.getName());
			sr.save(salesperson.get());
			return null;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Salesperson with SPID: " + spid + " not found");
		}
	}
}
