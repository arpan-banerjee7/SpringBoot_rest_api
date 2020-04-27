package com.spring.liquibase.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.liquibase.demo.dto.CustomerDto;
import com.spring.liquibase.demo.dto.CustomerDtoList;
import com.spring.liquibase.demo.model.Customer;
import com.spring.liquibase.demo.service.CustomerService;
import com.spring.liquibase.demo.utility.EntityToDtoMapper;
import com.spring.liquibase.demo.utility.PropertyService;

@RestController
public class HomeController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private EntityToDtoMapper mapper;
	
	@GetMapping(path="/customers",produces= {"application/xml"})
	public CustomerDtoList getCustomers(){
		CustomerDtoList cusDtoList=new CustomerDtoList();
	cusDtoList=customerService.getCustomers();
		return cusDtoList;
	}

	@GetMapping(path="/customer/{id}",produces= {"application/xml"})
	public CustomerDto getCustomer(@PathVariable("id")int id){
		CustomerDto customerDto=customerService.getCustomer(id);
		return customerDto;
	}
	
	@PostMapping("/customer")
	 public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto){
		String message="";
		ResponseEntity<String> finalMessage=null;
		try {
		
		Customer customer=mapper.mapToEntity(customerDto);
		customerService.addCustomer(customer);
		message="Customer with "+customer.getId()+" sucessfully added";
		finalMessage= new ResponseEntity<>(message, HttpStatus.OK);
		
	}catch(Exception e) {
		message="Failed to add customer due to "+e.getMessage();
		finalMessage= new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
	}
		return finalMessage;
	}

}
