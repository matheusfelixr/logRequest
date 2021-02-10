package com.matheusfelixr.logRequest.controller;

import com.matheusfelixr.logRequest.domain.Person;
import com.matheusfelixr.logRequest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    PersonService personService;

    @PostMapping("/save")
    public Mono<Person> save(@RequestBody Person person){
        return personService.save(person);
    }

    @GetMapping("/find-all")
    public Flux<Person> findAll(){
        return personService.findAll();
    }
}
