package com.sofka.sofkianos.repositories;

import com.sofka.sofkianos.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    Flux<Person> findAll();
}
