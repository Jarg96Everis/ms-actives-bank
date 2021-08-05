package com.bootcamp.msactives.services.impl;

import com.bootcamp.msactives.entities.ActiveType;
import com.bootcamp.msactives.repositories.ActiveTypeRepository;
import com.bootcamp.msactives.services.IActiveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActiveTypeServiceImpl implements IActiveTypeService {

    @Autowired
    private ActiveTypeRepository activeTypeDao;

    @Override
    public Flux<ActiveType> findAll() {
        return activeTypeDao.findAll();
    }

    @Override
    public Mono<ActiveType> findById(String id) {
        return activeTypeDao.findById(id);
    }

    @Override
    public Mono<ActiveType> save(ActiveType activeType) {
        return activeTypeDao.save(activeType);
    }

    @Override
    public Mono<Void> delete(ActiveType activeType) {
        return activeTypeDao.delete(activeType);
    }

    @Override
    public Mono<ActiveType> update(String id, ActiveType activeType) {
        return activeTypeDao.findById(id).flatMap( a -> {
            if (a == null){
                return null;
            }
            a.setName(activeType.getName());
            a.setDescription(activeType.getDescription());
            return Mono.just(a);
        });
    }
}
