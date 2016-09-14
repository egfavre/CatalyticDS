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

    ArrayList factors;

    public Factor() {
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

    public ArrayList getFactors() {
        return factors;
    }

    public void setFactors(ArrayList factors) {
        this.factors = factors;
    }
}
