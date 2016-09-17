package com.egfavre.services;

import com.egfavre.entities.Palindrome;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by user on 9/13/16.
 */
public interface PalindromeRepository extends CrudRepository<Palindrome, Integer> {
    Iterable<Palindrome> findByInput(String input);
}
