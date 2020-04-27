package com.spring.liquibase.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.liquibase.demo.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>  {

}
