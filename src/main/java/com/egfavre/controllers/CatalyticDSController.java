package com.egfavre.controllers;

import com.egfavre.entities.Factor;
import com.egfavre.entities.Fibonacci;
import com.egfavre.entities.Palindrome;
import com.egfavre.services.FactorRepository;
import com.egfavre.services.FibonacciRepository;
import com.egfavre.services.PalindromeRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 9/13/16.
 */

@Controller
public class CatalyticDSController {

        @Autowired
        FactorRepository factors;

        @Autowired
        PalindromeRepository palindromes;

        @Autowired
        FibonacciRepository fibonaccis;

        //create server connection
        @PostConstruct
        public void init() throws SQLException {
            Server.createWebServer().start();
        }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (HttpSession session) {
        return "home";
    }

    @RequestMapping (path = "/factor", method = RequestMethod.GET)
    public String factorPage (HttpSession session, Model model) {
        return "factor";
    }

    @RequestMapping (path = "/fibonacci", method = RequestMethod.GET)
    public String fibonacciPage (HttpSession session, Model model) {
        return "fibonacci";
    }

    @RequestMapping (path = "/palindrome", method = RequestMethod.GET)
    public String palindromePage (HttpSession session, Model model) {
        return "palindrome";
    }

    //find factors of the input
    public void findFactors (int input){
        ArrayList<Integer> factorResults = new ArrayList<Integer>();
        int start = 1;
        while (start <= input){
            if (input % start == 0){
                factorResults.add(start);
            }
            start++;
        }
        Factor factor = new Factor(input, factorResults);
        factors.save(factor);
    }

    //find the nth value of the standard fibonacci sequence
    public void findFibonacci (int input){
        ArrayList<Integer> answerSet = new ArrayList<>();
        int nth = 1;
        int a = 0;
        int b = 1;
        answerSet.add(a);
        answerSet.add(b);
        while (input >= nth){
            int c = a + b;
            a = b;
            b = c;
            answerSet.add(c);
            nth++;
        }
        Fibonacci fibonacci = new Fibonacci(input, answerSet);
        fibonaccis.save(fibonacci);
    }

    //Is text a palindrome?
    public void findPalindrome (String input){
        String finalInput = input.replaceAll("^[^\\p{L}^\\p{N}\\s%]+|[^\\p{L}^\\p{N}\\s%]+$", "");
        StringBuilder reverseBuilder = new StringBuilder();
        reverseBuilder.append(finalInput);
        reverseBuilder = reverseBuilder.reverse();
        String reverse = String.valueOf(reverseBuilder);
        Boolean isPalindrome = finalInput.equals(reverse);
        Palindrome palindrome = new Palindrome(input, isPalindrome);
        palindromes.save(palindrome);
    }


}
