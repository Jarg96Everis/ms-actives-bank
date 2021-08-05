package com.bootcamp.msactives.controllers;

import com.bootcamp.msactives.entities.ActiveType;
import com.bootcamp.msactives.services.IActiveTypeService;
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

@Slf4j(topic = "ACTIVETYPE_CONTROLLER")
@RestController
@RequestMapping("/api/activetype")
public class ActiveTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveTypeController.class);

    @Autowired
    private IActiveTypeService service;

    @GetMapping
    public Flux<ActiveType> listOfActiveType(){

        return service.findAll()
                .doOnNext(s -> LOGGER.info("listOfActiveType"));
    }

    @GetMapping("/find")
    public Mono<ResponseEntity<ActiveType>> findActiveType(@RequestParam String id){

        return service.findById(id).map(a -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(a))
                .doOnNext(a -> LOGGER.info("findByActiveType: activetype={}", a.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ActiveType>> newActiveType(@RequestBody ActiveType activetype){


        return service.save(activetype).map(a -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(a))
                .doOnNext(a -> LOGGER.info("newActiveType: activetypeId={}", a.getBody().getId()));
    }

    @PutMapping
    public Mono<ResponseEntity<ActiveType>> updateActiveType(@RequestParam String id, @RequestBody ActiveType activetype){

        return service.update(id, activetype).flatMap(a -> {
                    return service.save(a);
                }).map(a -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(a))
                .doOnNext(a -> LOGGER.info("updateActiveType: activetypeId={}", a.getBody().getId()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteActiveType(@RequestParam String id){

        return service.findById(id)
                .doOnNext(a -> LOGGER.info("deleteActiveType: activetypeId={}", a.getId()))
                .flatMap(a -> {
                    return  service.delete(a)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
