package com.example.HandlingNPEx;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> helloMono(ServerRequest request) {
        Greeting greeting = new Greeting("Hello, Spring! This is mono");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(greeting));
    }

    public Mono<ServerResponse> helloFlux(ServerRequest request) {
        Greeting greeting = new Greeting("Hello, Spring! This is mono");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(greeting));
    }

    private Mono<String> sayHello(ServerRequest request) {
//        Greeting greeting = new Greeting("Hello, Spring! This is mono");

//        Optional<String> queryparam = Optional.of(request.queryParam("name").get());
//        return Mono.just("Hello, " + queryparam.orElseGet(null));

        if(!request.queryParam("name").isPresent()){
            return Mono.just("Failed Name");
        }

        return Mono.justOrEmpty("Hello, " + request.queryParam("name").orElseGet(null));
        //...
    }

    public Mono<ServerResponse> handleOnReturn(ServerRequest request) {
        return sayHello(request)
                .onErrorReturn("Hey! onErrorReturn Handle: Sorry there is an Error ")
                .flatMap(s -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(s));
    }

    public Mono<ServerResponse> handleOnResume(ServerRequest request) {
        return sayHello(request)
                .flatMap(s -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(s))
                .onErrorResume(e -> Mono.just("Hey! onErrorResume Handle: Sorry there is an Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.ok()
                                .contentType(MediaType.TEXT_PLAIN)
                                .bodyValue(s)));
    }
}