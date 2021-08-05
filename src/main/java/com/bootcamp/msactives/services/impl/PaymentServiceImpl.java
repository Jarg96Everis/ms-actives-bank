package com.bootcamp.msactives.services.impl;

import com.bootcamp.msactives.entities.Payment;
import com.bootcamp.msactives.repositories.PaymentRepository;
import com.bootcamp.msactives.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepository paymentDao;

    @Override
    public Flux<Payment> findAll() {
        return paymentDao.findAll();
    }

    @Override
    public Mono<Payment> findById(String id) {
        return paymentDao.findById(id);
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        return paymentDao.save(payment);
    }

    @Override
    public Mono<Void> delete(Payment payment) {
        return paymentDao.delete(payment);
    }

    @Override
    public Mono<Payment> update(String id, Payment payment) {
        return paymentDao.findById(id).flatMap( p -> {
            if (p == null){
                return null;
            }
            p.setAmount(payment.getAmount());
            return Mono.just(p);
        });
    }
}
