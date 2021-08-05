package com.bootcamp.msactives.services.impl;

import com.bootcamp.msactives.entities.CustomerCredit;
import com.bootcamp.msactives.repositories.CustomerCreditRepository;
import com.bootcamp.msactives.services.ICustomerCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerCreditServiceImpl implements ICustomerCreditService {

    @Autowired
    private CustomerCreditRepository customerCreditDao;

    @Override
    public Flux<CustomerCredit> findAll() {
        return customerCreditDao.findAll();
    }

    @Override
    public Mono<CustomerCredit> findById(String id) {
        return customerCreditDao.findById(id);
    }

    @Override
    public Mono<CustomerCredit> save(CustomerCredit customerCredit) {
        return customerCreditDao.save(customerCredit);
    }

    @Override
    public Mono<Void> delete(CustomerCredit customerCredit) {
        return customerCreditDao.delete(customerCredit);
    }

    @Override
    public Mono<CustomerCredit> update(String id, CustomerCredit customerCredit) {
        return customerCreditDao.findById(id).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setCredit(customerCredit.getCredit());
            c.setId_Customer(customerCredit.getId_Customer());

            return Mono.just(c);
        });
    }
}
