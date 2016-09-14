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

    @Column(nullable = false)
    ArrayList answerSet;

    public Fibonacci() {
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

    public ArrayList getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(ArrayList answerSet) {
        this.answerSet = answerSet;
    }
}
