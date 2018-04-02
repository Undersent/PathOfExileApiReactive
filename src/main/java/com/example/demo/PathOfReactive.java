package com.example.demo;

import com.example.demo.handlers.AccountHandler;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class PathOfReactive {

	public static void main(String[] args) {
		SpringApplication.run(PathOfReactive.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, AccountRepository repository) {
		return args -> {
//			Flux<Account> productFlux = Flux.just(
//					new Account(null, "Zabijacz potforuw", 3),
//					new Account(null, "Miecznik gwiazdzisty", 2),
//					new Account(null, "Erni dwunasty", 1))
//					.flatMap(repository::save);

//			productFlux
//					.thenMany(repository.findAll())
//					.subscribe(System.out::println);
		};
	}

	@Bean
	RouterFunction<ServerResponse> routes(AccountHandler handler) {
		return route(GET("/accounts").and(accept(APPLICATION_JSON)), handler::getAllAccounts)
				.andRoute(POST("/accounts").and(contentType(APPLICATION_JSON)), handler::saveAccount)
				.andRoute(GET("/account/{id}").and(accept(APPLICATION_JSON)), handler::getAccount)
				.andRoute(PUT("/account/{id}").and(contentType(APPLICATION_JSON)), handler::updateAccount);

	}
}
