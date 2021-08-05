package com.bootcamp.msactives.controllers;

import com.bootcamp.msactives.entities.Credit;
import com.bootcamp.msactives.services.ICreditService;
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

@Slf4j(topic = "CREDIT_CONTROLLER")
@RestController
@RequestMapping("/api/credit")
public class CreditController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditController.class);

    @Autowired
    private ICreditService service;

    @GetMapping
    public Flux<Credit> listOfCredit(){

        return service.findAll()
                .doOnNext(s -> LOGGER.info("listOfCredit"));
    }

    @GetMapping("/find")
    public Mono<ResponseEntity<Credit>> findCredit(@RequestParam String id){

        return service.findById(id).map(c -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .doOnNext(c -> LOGGER.info("findByCredit: creditId={}", c.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Credit>> newCredit(@RequestBody Credit credit){

                credit.setCreateAt(new Date());

        return service.save(credit).map(c -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .doOnNext(c -> LOGGER.info("newCredit: creditId={}", c.getBody().getId()));
    }

    @PutMapping
    public Mono<ResponseEntity<Credit>> updateCredit(@RequestParam String id, @RequestBody Credit credit){

        return service.update(id, credit).flatMap(c -> {
            return service.save(c);
                }).map(c -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .doOnNext(c -> LOGGER.info("updateCredit: creditId={}", c.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteCredit(@RequestParam String id){

        return service.findById(id)
                .doOnNext(c -> LOGGER.info("deleteCredit: creditId={}", c.getId()))
                .flatMap(c -> {
            return  service.delete(c)
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }


}
