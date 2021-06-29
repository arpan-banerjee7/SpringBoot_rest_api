package com.spring.liquibase.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.liquibase.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	public Customer findFirstByGenderAndLastName(String gneder,String lastName);
	public List<Customer> findByGenderAndLastName(String gneder,String lastName);
}
