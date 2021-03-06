package com.egfavre.entities;

import javax.persistence.*;

/**
 * Created by user on 9/13/16.
 */

@Entity
@Table(name = "palindromes")
public class Palindrome {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String input;

    @Column(nullable = false)
    String reverse;

    @Column(nullable = false)
    Boolean isPalindrome;

    public Palindrome() {
    }

    public Palindrome(String input, String reverse, Boolean isPalindrome) {
        this.input = input;
        this.reverse = reverse;
        this.isPalindrome = isPalindrome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Boolean getPalindrome() {
        return isPalindrome;
    }

    public void setPalindrome(Boolean palindrome) {
        isPalindrome = palindrome;
    }
}