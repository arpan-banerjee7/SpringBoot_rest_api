package com.spring.liquibase.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.liquibase.demo.dto.CustomerDto;
import com.spring.liquibase.demo.dto.CustomerDtoList;
import com.spring.liquibase.demo.model.Customer;
import com.spring.liquibase.demo.repository.CustomerRepository;
import com.spring.liquibase.demo.utility.EntityToDtoMapper;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EntityToDtoMapper entityToDto;
	
	public CustomerDtoList getCustomers(){
		List<Customer>customerList=customerRepository.findAll();
		CustomerDtoList customerDtoList=new CustomerDtoList();
		//List<CustomerDto> cusDtoList=new ArrayList<>();
		
		
		for (Customer customer : customerList) {
			CustomerDto customerDto=entityToDto.mapToDto(customer);
			customerDtoList.addCustomerDto(customerDto);
			//cusDtoList.add(customerDto);
		}
	
		return customerDtoList;
	}
	
	public CustomerDto getCustomer(int id) {
		Customer customer = customerRepository.findById(id).orElse(new Customer());
		CustomerDto customerDto=entityToDto.mapToDto(customer);
		return customerDto;
	}
	
	public Customer addCustomer(Customer customer) {
	    customerRepository.save(customer);
	    return customer;
	}
	
}
