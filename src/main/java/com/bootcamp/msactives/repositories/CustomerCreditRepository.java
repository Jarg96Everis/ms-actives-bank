package com.bootcamp.msactives.repositories;

import com.bootcamp.msactives.entities.CustomerCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerCreditRepository extends ReactiveMongoRepository<CustomerCredit,String> {
}
