package com.bootcamp.msactives.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bootcamp.msactives.entities.Customer_credit.Customer_creditBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "active_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activetype {

	@Id
	private String id;
}
