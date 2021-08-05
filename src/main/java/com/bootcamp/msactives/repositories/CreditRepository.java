package com.bootcamp.msactives.repositories;

import com.bootcamp.msactives.entities.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditRepository extends ReactiveMongoRepository<Credit,String> {
}
