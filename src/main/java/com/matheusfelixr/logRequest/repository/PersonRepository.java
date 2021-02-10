package com.matheusfelixr.logRequest.repository;

import com.matheusfelixr.logRequest.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person, String>{

}
