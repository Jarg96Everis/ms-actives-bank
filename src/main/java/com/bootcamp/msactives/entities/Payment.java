package com.bootcamp.msactives.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

	@Id
	private String id;
	
	private double amount;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date datePayment;
	
	private CustomerCredit customer_credit;
	
}
