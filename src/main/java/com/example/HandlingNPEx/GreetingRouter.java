package com.example.HandlingNPEx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> routeMono(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/mono")
                        .and(accept(MediaType.APPLICATION_JSON)), greetingHandler::helloMono);
    }

    @Bean
    public RouterFunction<ServerResponse> routeFlux(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/flux")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::helloFlux);
    }

    @Bean
    public RouterFunction<ServerResponse> routeReturn(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/onReturn")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                greetingHandler::handleOnReturn);
    }

    @Bean
    public RouterFunction<ServerResponse> routeResume(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/onResume")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                greetingHandler::handleOnResume);
    }
}