package com.spring.liquibase.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.liquibase.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
