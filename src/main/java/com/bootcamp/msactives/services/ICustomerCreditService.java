package com.bootcamp.msactives.services;

import com.bootcamp.msactives.entities.Credit;
import com.bootcamp.msactives.entities.CustomerCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerCreditService {

    public Flux<CustomerCredit> findAll();

    public Mono<CustomerCredit> findById(String id);

    public Mono<CustomerCredit> save(CustomerCredit customerCredit);

    public Mono<Void> delete(CustomerCredit customerCredit);

    public Mono<CustomerCredit> update(String id, CustomerCredit customerCredit);
}
