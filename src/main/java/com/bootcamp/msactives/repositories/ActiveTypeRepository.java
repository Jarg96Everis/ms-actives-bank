package com.bootcamp.msactives.repositories;

import com.bootcamp.msactives.entities.ActiveType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ActiveTypeRepository extends ReactiveMongoRepository<ActiveType,String> {
}
