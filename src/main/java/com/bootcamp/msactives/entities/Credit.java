package com.bootcamp.msactives.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Document(collection = "credit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {

	@Id
	private String id;
	
	private Activetype active_type;

	private double capital;

	@Column(name = "fecalta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	private double commission;

	private double interestRate;

	private int numOfInstallments;

	private double creditLifeIns;
}
