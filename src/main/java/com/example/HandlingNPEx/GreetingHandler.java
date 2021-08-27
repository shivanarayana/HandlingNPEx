package com.example.HandlingNPEx;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hellomono(ServerRequest request) {
        Greeting greeting = new Greeting("Hello, Spring! This is mono");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(greeting));
    }

    public Mono<ServerResponse> helloflux(ServerRequest request) {
        Greeting greeting = new Greeting("Hello, Spring! This is mono");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(greeting));
    }
}