package com.spring.liquibase.demo.controller;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
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
@SpringBootTest

class HomeControllerTest {
	private MockMvc mvc;

	@InjectMocks
	private HomeController homeController;

	@MockBean
	private CustomerService customerService;

	@Mock
	private PropertyService propertyService;
	
	@Mock
	private Environment env;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(homeController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testaddCustomer() throws Exception {
		String uri = "/customer";
		CustomerDto custDto = this.mockCustomerObject();
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
		cusDto.setAuthId(" 6AE-BH3-24F-67FG-76G-345G-AGF6H");
//		cusDto.setAuthId(propertyService.getKeytoAddCustomer());
//		System.out.println(propertyService.getKeytoAddCustomer());
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
