package com.egfavre.services;

import com.egfavre.entities.Fibonacci;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by user on 9/13/16.
 */
public interface FibonacciRepository extends CrudRepository<Fibonacci, Integer> {
    List<Fibonacci> findByInput(Integer input);
}
