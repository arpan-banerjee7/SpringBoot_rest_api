package com.spring.liquibase.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sun.xml.txw2.annotation.XmlElement;


@JacksonXmlRootElement(localName = "Customer")
public class CustomerDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private Date date;
	private AddressDto addressdto;
	
	
	public CustomerDto() {
		super();
	}
	
	
	


	public AddressDto getAddressdto() {
		return addressdto;
	}
	public void setAddressdto(AddressDto addressdto) {
		this.addressdto = addressdto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
