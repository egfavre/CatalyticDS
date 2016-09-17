package com.egfavre.entities;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by user on 9/13/16.
 */
@Entity
@Table(name = "factors")
public class Factor {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    int input;

    @Column(length = 10000)
    String factorResults;

    public Factor() {
    }

    public Factor(int input, String factorResults) {
        this.input = input;
        this.factorResults = factorResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public String getFactorResults() {
        return factorResults;
    }

    public void setFactorResults(String factorResults) {
        this.factorResults = factorResults;
    }
}
