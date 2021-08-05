package com.bootcamp.msactives.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bootcamp.msactives.entities.Payment.PaymentBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "customer_credit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer_credit {

	@Id
	private String id;
	
	private String id_Customer;
	
	private Credit credit;
}
