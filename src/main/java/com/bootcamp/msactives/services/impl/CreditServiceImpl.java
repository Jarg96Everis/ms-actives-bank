package com.bootcamp.msactives.services.impl;

import com.bootcamp.msactives.entities.Credit;
import com.bootcamp.msactives.repositories.CreditRepository;
import com.bootcamp.msactives.services.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    private CreditRepository creditDao;

    @Override
    public Flux<Credit> findAll() {
        return creditDao.findAll();
    }

    @Override
    public Mono<Credit> findById(String id) {
        return creditDao.findById(id);
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        return creditDao.save(credit);
    }

    @Override
    public Mono<Void> delete(Credit credit) {
        return creditDao.delete(credit);
    }

    @Override
    public Mono<Credit> update(String id, Credit credit) {
        return creditDao.findById(id).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setCapital(credit.getCapital());
            c.setCreditLifeIns(credit.getCreditLifeIns());
            c.setCommission(credit.getCommission());
            c.setInterestRate(credit.getInterestRate());
            c.setNumOfInstallments(credit.getNumOfInstallments());

            return Mono.just(c);
        });
    }
}
