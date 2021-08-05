package com.bootcamp.msactives.services;

import com.bootcamp.msactives.entities.Credit;
import com.bootcamp.msactives.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPaymentService {

    public Flux<Payment> findAll();

    public Mono<Payment> findById(String id);

    public Mono<Payment> save(Payment payment);

    public Mono<Void> delete(Payment payment);

    public Mono<Payment> update(String id, Payment payment);
}
