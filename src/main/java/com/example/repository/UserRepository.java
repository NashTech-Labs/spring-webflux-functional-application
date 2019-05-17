package com.example.repository;

import com.example.model.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCassandraRepository<User, Integer> {

    Flux<User> findAll();
    Mono<User> findById(int id);
    Mono<User> save(User user);
}

