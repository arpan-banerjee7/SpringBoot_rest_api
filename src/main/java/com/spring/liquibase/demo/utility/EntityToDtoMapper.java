package com.spring.liquibase.demo.utility;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.spring.liquibase.demo.dto.AddressDto;
import com.spring.liquibase.demo.dto.CustomerDto;
import com.spring.liquibase.demo.model.Address;
import com.spring.liquibase.demo.model.Customer;

@Component
public class EntityToDtoMapper {

	
//	public CustomerDto mapToDto(Customer customer){
//		ModelMapper modelMapper = new ModelMapper();
//		// user here is a prepopulated User instance
//		CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
//		return customerDto;
//	}
	
	public CustomerDto mapToDto(Customer customer) {
		AddressDto addressDto=new AddressDto();
		addressDto.setCity(customer.getAddress().getCity());
		addressDto.setCountry(customer.getAddress().getCountry());
		addressDto.setId(customer.getAddress().getId());
		CustomerDto customerDto=new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setGender(customer.getGender());
		customerDto.setDate(customer.getDate());
		customerDto.setAddressdto(addressDto);
		return customerDto;
	}
	public Customer mapToEntity(CustomerDto customerDto) {
		Address address=new Address();
		address.setCity(customerDto.getAddressdto().getCity());
		address.setCountry(customerDto.getAddressdto().getCountry());
		//address.setId(customerDto.getAddressdto().getId());
		
		
		Customer customer=new Customer();
		//customer.setId(customerDto.getId());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setGender(customerDto.getGender());
		customer.setDate(customerDto.getDate());
		customer.setAddress(address);
		address.setCustomer(customer);
		return customer;	
	}
//	public Customer mapToEntity(CustomerDto customerDto) {
//		ModelMapper modelMapper = new ModelMapper();
//		// user here is a prepopulated User instance
//		Customer customer = modelMapper.map(customerDto, Customer.class);
//		return customer;
//	}
	
}
