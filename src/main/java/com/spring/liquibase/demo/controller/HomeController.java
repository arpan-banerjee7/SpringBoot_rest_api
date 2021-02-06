package com.spring.liquibase.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.liquibase.demo.dto.CustomerDto;
import com.spring.liquibase.demo.dto.CustomerDtoList;
import com.spring.liquibase.demo.model.Customer;
import com.spring.liquibase.demo.service.CustomerService;
import com.spring.liquibase.demo.utility.EntityToDtoMapper;
import com.spring.liquibase.demo.utility.PropertyService;

@RestController
@CrossOrigin
public class HomeController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private PropertyService propertyService;

	@Value("${customer.auth.key}")
	private String customerAuthKey;

	@Autowired
	private EntityToDtoMapper mapper;

	@GetMapping(path = "/customers", produces = { "application/xml" })
	public CustomerDtoList getCustomers() {

		CustomerDtoList cusDtoList = new CustomerDtoList();
		cusDtoList = customerService.getCustomers();
		return cusDtoList;
	}

	@GetMapping(path = "/customer/{id}", produces = { "application/xml" })
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") int id,
			@RequestHeader("authKey") String language) {
		System.out.println(propertyService.getKeytoAddCustomer());
		// validation through headers
		if (language.equals(customerAuthKey)) {
			CustomerDto customerDto = customerService.getCustomerById(id);
			return new ResponseEntity<>(customerDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path="/customer",consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto) {
		String message = "";
		ResponseEntity<String> finalMessage = null;
		try {
			if ((!customerDto.getAuthId().equals(propertyService.getKeytoAddCustomer()))) {
				System.out.println("If check failed: "+propertyService.getKeytoAddCustomer());
				System.out.println("Unauthorized access attempted");
				message = "Unauthorized access attempted";
				finalMessage = new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
				return finalMessage;
			}
			System.out.println("If check passed :"+propertyService.getKeytoAddCustomer());

			Customer customer = mapper.mapToEntity(customerDto);
			customerService.addCustomer(customer);
			message = "Customer with " + customer.getId() + " sucessfully added";
			finalMessage = new ResponseEntity<>(message, HttpStatus.OK);

		} catch (Exception e) {
			message = "Failed to add customer due to " + e.getMessage();
			finalMessage = new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return finalMessage;
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		String message = "";
		ResponseEntity<String> finalMessage = null;
		try {
			customerService.deleteCustomerById(id);
			message = "Customer with " + id + " successfully deleted";
			finalMessage = new ResponseEntity<>(message, HttpStatus.OK);
			return finalMessage;
		} catch (Exception e) {
			message = "Failed to delete customer due to :" + e.getMessage();
			finalMessage = new ResponseEntity<>(message, HttpStatus.GONE);
		}
		return finalMessage;
	}

	public String test() {
		String s = customerService.test();
		return s;
	}
	
	
}
