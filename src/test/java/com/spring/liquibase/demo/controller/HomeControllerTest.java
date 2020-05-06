package com.spring.liquibase.demo.controller;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.liquibase.demo.dto.AddressDto;
import com.spring.liquibase.demo.dto.CustomerDto;
import com.spring.liquibase.demo.service.CustomerService;
import com.spring.liquibase.demo.utility.PropertyService;

@ExtendWith(SpringExtension.class)
class HomeControllerTest {
	private MockMvc mvc;
	
	@InjectMocks
	private HomeController homeController;
	
	@MockBean
	private CustomerService customerService;

	
	
	@BeforeEach
	public void setup() {
		mvc=MockMvcBuilders.standaloneSetup(homeController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testaddCustomer() throws Exception{
		String uri="/customer";
		CustomerDto custDto=this.mockCustomerObject();
		String actualResult=mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(custDto)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		Assertions.assertEquals(actualResult, "Customer with "+custDto.getId()+" sucessfully added");
	}

	private CustomerDto mockCustomerObject() {
		CustomerDto cusDto=new CustomerDto();
		AddressDto addressDto=new AddressDto();
		addressDto.setCity("BBSR");
		addressDto.setCountry("INDIA");
	//	addressDto.setId(121);
		cusDto.setDate(new Date());
		cusDto.setFirstName("Bipro");
		cusDto.setLastName("KAR");
		cusDto.setGender("M");
		//cusDto.setId(121);
	//	cusDto.setAuthKey(propertyService.getKeytoAddCustomer());
		cusDto.setAddressdto(addressDto);
		return cusDto;
		
	}
	public static String asJsonString(CustomerDto cusDto) {
	    try {
	        return new ObjectMapper().writeValueAsString(cusDto);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
