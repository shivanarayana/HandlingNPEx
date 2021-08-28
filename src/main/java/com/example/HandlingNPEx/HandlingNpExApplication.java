package com.example.HandlingNPEx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Mono;

import java.util.Optional;

@SpringBootApplication
public class HandlingNpExApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HandlingNpExApplication.class, args);

		GreetingClient greetingClient = context.getBean(GreetingClient.class);
		// We need to block for the content here or the JVM might exit before the message is logged
		System.out.println(">> message = " + greetingClient.getMessageMono().block());

		System.out.println("-- Creating Mono with justOrEmpty() --");
		Mono.justOrEmpty("apple")
				.subscribe(System.out::println);

		System.out.println("-- Using justOrEmpty() with null --");
		Mono.justOrEmpty(null)
				.subscribe(System.out::println);

		System.out.println("-- Creating Mono with optional --");
		Optional<String> optional = Optional.of("apple");
		Mono.justOrEmpty(optional)
				.subscribe(System.out::println);

		System.out.println("-- Creating Mono with null optional --");
		Optional<String> optional2 = Optional.ofNullable(null);
		Mono.justOrEmpty(optional2)
				.subscribe(System.out::println);

		System.out.println("-- Creating Mono with empty optional --");
		Optional<String> optional3 = Optional.empty();
		Mono.justOrEmpty(optional3)
				.subscribe(System.out::println);
	}
}