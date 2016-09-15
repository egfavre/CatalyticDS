package com.egfavre.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    @Column(nullable = false)
    Integer answer;

    public Fibonacci() {
    }

    public Fibonacci(int input, Integer answer) {
        this.input = input;
        this.answer = answer;
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

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
