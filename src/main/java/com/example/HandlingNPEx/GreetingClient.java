package com.example.HandlingNPEx;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GreetingClient {

    private final WebClient client;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public GreetingClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessageMono() {
        return this.client.get().uri("/mono").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }

    public Flux<String> getMessageFlux() {
        return this.client.get().uri("/flux").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Greeting.class)
                .map(Greeting::getMessage);
    }

//    public Flux<String> getMessageReturn() {
//        return this.client.get().uri("/onReturn").accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Greeting.class)
//                .map(Greeting::getMessage);
//    }
//
//    public Flux<String> getMessageResume() {
//        return this.client.get().uri("/onResume").accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Greeting.class)
//                .map(Greeting::getMessage);
//    }
}