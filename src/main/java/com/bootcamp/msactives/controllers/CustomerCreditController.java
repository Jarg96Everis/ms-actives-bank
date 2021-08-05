package com.bootcamp.msactives.controllers;

import com.bootcamp.msactives.entities.Credit;
import com.bootcamp.msactives.entities.CustomerCredit;
import com.bootcamp.msactives.services.ICreditService;
import com.bootcamp.msactives.services.ICustomerCreditService;
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

@Slf4j(topic = "CUSTOMERCREDIT_CONTROLLER")
@RestController
@RequestMapping("/api/customercredit")
public class CustomerCreditController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerCreditController.class);

    @Autowired
    private ICustomerCreditService service;

    @GetMapping
    public Flux<CustomerCredit> listOfCustomerCredit(){

        return service.findAll()
                .doOnNext(s -> LOGGER.info("listOfCredit"));
    }

    @GetMapping("/find")
    public Mono<ResponseEntity<CustomerCredit>> findCustomerCredit(@RequestParam String id){

        return service.findById(id).map(c -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(c))
                .doOnNext(c -> LOGGER.info("findByCredit: customercreditId={}", c.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerCredit>> newCustomerCredit(@RequestBody CustomerCredit customercredit){

        customercredit.setCreatAt(new Date());

        return service.save(customercredit).map(c -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(c))
                .doOnNext(c -> LOGGER.info("newCredit: customercreditId={}", c.getBody().getId()));
    }

    @PutMapping
    public Mono<ResponseEntity<CustomerCredit>> updateCustomerCredit(@RequestParam String id, @RequestBody CustomerCredit customercredit){

        return service.update(id, customercredit).flatMap(c -> {
                    return service.save(c);
                }).map(c -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(c))
                .doOnNext(c -> LOGGER.info("updateCredit: customercreditId={}", c.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteCustomerCredit(@RequestParam String id){

        return service.findById(id)
                .doOnNext(c -> LOGGER.info("deleteCredit: customercreditId={}", c.getId()))
                .flatMap(c -> {
                    return  service.delete(c)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
