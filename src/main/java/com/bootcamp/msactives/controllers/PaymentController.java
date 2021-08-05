package com.bootcamp.msactives.controllers;

import com.bootcamp.msactives.entities.Payment;
import com.bootcamp.msactives.services.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j(topic = "PAYMENT_CONTROLLER")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private IPaymentService service;

    @GetMapping
    public Flux<Payment> listOfPayment(){

        return service.findAll()
                .doOnNext(s -> LOGGER.info("listOfPayment"));
    }

    @GetMapping("/find")
    public Mono<ResponseEntity<Payment>> findPayment(@RequestParam String id){

        return service.findById(id).map(p -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .doOnNext(p -> LOGGER.info("findPayment: paymentId={}", p.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Payment>> newPayment(@RequestBody Payment payment){

        payment.setDatePayment(new Date());

        return service.save(payment).map(p -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .doOnNext(p -> LOGGER.info("newPayment: paymentId={}", p.getBody().getId()));
    }

    @PutMapping
    public Mono<ResponseEntity<Payment>> updatePayment(@RequestParam String id, @RequestBody Payment payment){

        return service.update(id, payment).flatMap(c -> {
                    return service.save(c);
                }).map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .doOnNext(p -> LOGGER.info("updatePayment: paymentId={}", p.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<Void>> deletePayment(@RequestParam String id){

        return service.findById(id)
                .doOnNext(p -> LOGGER.info("deletePayment: paymentId={}", p.getId()))
                .flatMap(p -> {
                    return  service.delete(p)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
