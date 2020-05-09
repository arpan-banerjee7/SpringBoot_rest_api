package com.spring.liquibase.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.liquibase.demo.dto.CustomerDto;

@Component

public class ClientUtility {

//here with spring boot u cannot access the prop from porp files in a separate main class.
	@Value("${customer.auth.key}")
	private String customerAuthKey;

	@Autowired
	private PropertyService propertyService;

	
	public void getCustomers() {
		String url = "http://localhost:8080/customer/1";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		// add basic authentication header
		headers.set("authKey", "6AE-BH3-24F-67FG-76G-345G-AGF6H");
		System.out.println(customerAuthKey);
		//System.out.println(propertyService.getKeytoAddCustomer());
		// build the request
		HttpEntity<CustomerDto> request = new HttpEntity<CustomerDto>(headers);

		ResponseEntity<CustomerDto> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDto.class);
		// CustomerDto customerDto=
		// restTemplate.getForObject("http://localhost:8080/customer/1",CustomerDto.class);
		// System.out.println(customerDto.getFirstName());
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Request Successful.");
			System.out.println(response.getBody().getFirstName());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
	}

	public static void main(String[] args) {
		
	
		
		ClientUtility clientUtility= new ClientUtility();
		clientUtility.getCustomers();

	}

}
