package com.spring.liquibase.demo.controller;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.liquibase.demo.dto.AddressDto;
import com.spring.liquibase.demo.dto.CustomerDto;
import com.spring.liquibase.demo.model.Address;
import com.spring.liquibase.demo.model.Customer;
import com.spring.liquibase.demo.service.CustomerService;
import com.spring.liquibase.demo.utility.EntityToDtoMapper;
import com.spring.liquibase.demo.utility.PropertyService;


@WebMvcTest(HomeController.class)
class HomeControllerTest {
	@Autowired
	private MockMvc mvc;


	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private Environment env;

	@MockBean
	private PropertyService propertyService;
	
	@MockBean
	private EntityToDtoMapper mapper;
	
	@Test
	public void testaddCustomer() throws Exception {
	    String uri = "/customer";
	    CustomerDto custDto = this.mockCustomerObject();
	    Customer customer = getCustomerEntity();
	    Mockito.when(mapper.mapToEntity(Mockito.any(CustomerDto.class))).thenReturn(customer);
	    String actualResult = mvc
	            .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(custDto)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
	    Assertions.assertEquals(actualResult, "Customer with " + custDto.getId() + " sucessfully added");
	}


	private CustomerDto mockCustomerObject() {
		CustomerDto cusDto = new CustomerDto();
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("BBSR");
		addressDto.setCountry("INDIA");
		cusDto.setDate(new Date());
		cusDto.setFirstName("Biprojeet");
		cusDto.setLastName("KAR");
		cusDto.setGender("M");
		cusDto.setAuthId("6AE-BH3-24F-67FG-76G-345G-AGF6H");
//		cusDto.setAuthId(propertyService.getKeytoAddCustomer());
//		System.out.println(propertyService.getKeytoAddCustomer());
		cusDto.setAddressdto(addressDto);
		return cusDto;

	}

	private Customer getCustomerEntity() {
	    Customer customer = new Customer();
	    Address address = new Address();
	    address.setCity("BBSR");
	    address.setCountry("INDIA");
	    customer.setDate(new Date());
	    customer.setFirstName("Biprojeet");
	    customer.setLastName("KAR");
	    customer.setGender("M");
	    customer.setAddress(address);
	    return customer;

	}
	public static String asJsonString(CustomerDto cusDto) {
		try {
			return new ObjectMapper().writeValueAsString(cusDto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
