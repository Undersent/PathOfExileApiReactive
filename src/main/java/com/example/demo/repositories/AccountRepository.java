package com.example.demo.repositories;

import com.example.demo.models.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AccountRepository extends ReactiveMongoRepository<Account, String> {


}
