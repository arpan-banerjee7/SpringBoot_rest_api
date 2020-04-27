package com.spring.liquibase.demo.utility;

import org.springframework.web.client.RestTemplate;

import com.spring.liquibase.demo.dto.CustomerDto;

public class ClientUtility {
	
	public static void getCustomers() {
		RestTemplate restTemplate = new RestTemplate();
		CustomerDto customerDto= restTemplate.getForObject("http://localhost:8080/customer/1",CustomerDto.class);
		System.out.println(customerDto.getFirstName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getCustomers();
		
	}

}
