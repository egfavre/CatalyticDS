package com.egfavre.services;

import com.egfavre.entities.Factor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user on 9/13/16.
 */
public interface FactorRepository extends CrudRepository<Factor, Integer> {
    Iterable<Factor> findByInput(Integer input);
}
