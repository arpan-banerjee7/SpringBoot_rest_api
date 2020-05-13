package com.spring.liquibase.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "CustomerList")
public class CustomerDtoList {

	@JacksonXmlProperty(localName = "Customer")
    @JacksonXmlElementWrapper(useWrapping = false)
	private List<CustomerDto> customerDtoList=new ArrayList<>();
	
	public void addCustomerDto(CustomerDto customerDto) {
		
		customerDtoList.add(customerDto);
		
	}

	public CustomerDtoList() {
		super();
	}
	
	public List<CustomerDto> getCustomerDtoList() {
		return customerDtoList;
	}

	public void setCustomerDtoList(List<CustomerDto> customerDtoList) {
		this.customerDtoList = customerDtoList;
	}

	
	
}
