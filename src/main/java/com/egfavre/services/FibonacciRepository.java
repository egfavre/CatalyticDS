package com.egfavre.services;

import com.egfavre.entities.Fibonacci;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user on 9/13/16.
 */
public interface FibonacciRepository extends CrudRepository<Fibonacci, Integer> {
}
