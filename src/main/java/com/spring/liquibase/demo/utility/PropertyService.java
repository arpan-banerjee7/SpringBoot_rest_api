package com.spring.liquibase.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config.properties")
public class PropertyService {
	@Autowired
	private Environment env;
	
	public String getKeytoAddCustomer() {
		return env.getProperty("auth.key.to.add.customer");
	}
}
