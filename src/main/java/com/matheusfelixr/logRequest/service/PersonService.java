package com.matheusfelixr.logRequest.service;

import com.matheusfelixr.logRequest.domain.Person;
import com.matheusfelixr.logRequest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PersonService{
	
	@Autowired
	private PersonRepository personRepository;

	public Flux<Person> findAll() {
		return personRepository.findAll();
	}

	public Mono<Person> findById(String id) {
		return personRepository.findById(id);
	}

	public Mono<Person>  save(Person person) {
		return personRepository.save(person);
	}

}
