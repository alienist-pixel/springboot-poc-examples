package com.code.javainuse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.code.javainuse.model.Person;

@SpringBootApplication
public class SimpleTransactionManagementApplication {
	
	@Autowired
	@Qualifier("student")
	Person p;

	public static void main(String[] args) {
		SpringApplication.run(SimpleTransactionManagementApplication.class, args);
	}

}
