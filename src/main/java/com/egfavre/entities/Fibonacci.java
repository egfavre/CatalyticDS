package com.egfavre.entities;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by user on 9/13/16.
 */
@Entity
@Table(name = "fibonaccis")
public class Fibonacci {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    int input;

    @Column(nullable = false, length = 1000000000)
    String answerSet;

    public Fibonacci() {
    }

    public Fibonacci(int input, String answerSet) {
        this.input = input;
        this.answerSet = answerSet;
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

    public String getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(String answerSet) {
        this.answerSet = answerSet;
    }
}
