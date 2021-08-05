package com.bootcamp.msactives.repositories;

import com.bootcamp.msactives.entities.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentRepository extends ReactiveMongoRepository<Payment,String> {
}
