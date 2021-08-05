package com.bootcamp.msactives.services;

import com.bootcamp.msactives.entities.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {

    public Flux<Credit> findAll();

    public Mono<Credit> findById(String id);

    public Mono<Credit> save(Credit credit);

    public Mono<Void> delete(Credit credit);

    public Mono<Credit> update(String id, Credit credit);
}
