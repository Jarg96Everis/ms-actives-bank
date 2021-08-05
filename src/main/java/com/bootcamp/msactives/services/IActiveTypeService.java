package com.bootcamp.msactives.services;

import com.bootcamp.msactives.entities.ActiveType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IActiveTypeService {

    public Flux<ActiveType> findAll();

    public Mono<ActiveType> findById(String id);

    public Mono<ActiveType> save(ActiveType activeType);

    public Mono<Void> delete(ActiveType activeType);

    public Mono<ActiveType> update(String id, ActiveType activeType);
}
