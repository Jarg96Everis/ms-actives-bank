package com.bootcamp.msactives.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer_credit")
public class Customer_credit {

	@Id
	private String id;
	
	private String id_Customer;
	
	private Credit credit;
}
