package com.example.demo.handlers;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class AccountHandler {

    private AccountRepository accountRepository;

    public AccountHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<ServerResponse> getAllAccounts(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountRepository.findAll(), Account.class);
    }

    public Mono<ServerResponse> getAccount(ServerRequest serverRequest) {
        return this.accountRepository
                .findById(serverRequest
                        .pathVariable("id"))
                .flatMap(account ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromObject(account)))
                .switchIfEmpty(ServerResponse
                        .notFound()
                        .build());
    }

    public Mono<ServerResponse> saveAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Account.class)
                .flatMap(account ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(accountRepository.save(account), Account.class));
    }

    public Mono<ServerResponse> updateAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Account.class)
                .zipWith(this.accountRepository.findById(serverRequest.pathVariable("id")),
                        (account, existingAccount) ->
                                new Account(existingAccount.getId(), account.getName(), account.getAge()))
                .flatMap(account ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(accountRepository.save(account), Account.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
