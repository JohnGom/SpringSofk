package com.sofka.sofkianos.controllers;

import com.sofka.sofkianos.model.Person;
import com.sofka.sofkianos.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ControllerHandler {

    @Autowired
    PersonRepository personRepository;

    public Mono<ServerResponse> savePerson(ServerRequest request) {
        Mono<Person> person = request.bodyToMono(Person.class);
        Disposable response = personRepository.insert(person).subscribe();
        return ServerResponse.ok().body(BodyInserters.fromObject(response));
}

    public Mono<ServerResponse> getMembers(ServerRequest request) {
        Flux<Person> person = personRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(person, Person.class);
    }

    public Mono<ServerResponse> deletePerson(ServerRequest request) {
        String id = request.queryParam("id").get();
        personRepository.deleteById(id).subscribe();
        return ServerResponse.ok().body(BodyInserters.fromObject("ok"));
    }

    public Mono<ServerResponse> updatePerson(ServerRequest request) {
        Mono<Person> person = request.bodyToMono(Person.class);
        person.subscribe(personUp -> personRepository.save(personUp).subscribe());
        return ServerResponse.ok().body(BodyInserters.fromObject("Ok"));
    }
}
