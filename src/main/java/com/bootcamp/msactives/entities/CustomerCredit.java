package com.bootcamp.msactives.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bootcamp.msactives.entities.Payment.PaymentBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "customer_credit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCredit {

	@Id
	private String id;
	
	private String id_Customer;
	
	private Credit credit;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date creatAt;
}
