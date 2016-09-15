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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Array;
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

    //add pages in local 8080
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (HttpSession session) {
        return "home";
    }

    @RequestMapping (path = "/factor", method = RequestMethod.GET)
    public String factorPage (HttpSession session, Model model) {
        Iterable<Factor> factorlist;
        factorlist = factors.findAll();
        model.addAttribute("factorList", factorlist);
        return "factor";
    }

    @RequestMapping (path = "/fibonacci", method = RequestMethod.GET)
    public String fibonacciPage (HttpSession session, Model model) {
        Iterable<Fibonacci> fibonacciList;
        fibonacciList = fibonaccis.findAll();
        model.addAttribute("fibonacciList", fibonacciList);
        return "fibonacci";
    }

    @RequestMapping (path = "/palindrome", method = RequestMethod.GET)
    public String palindromePage (HttpSession session, Model model) {
        Iterable<Palindrome> palindromeList;
        palindromeList = palindromes.findAll();
        model.addAttribute("palindromeList", palindromeList);
        return "palindrome";
    }

    @RequestMapping (path = "/factorNumber", method = RequestMethod.POST)
    public String factorNumber (HttpSession session, HttpServletRequest request, int input) throws Exception {
        findFactors(input);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping (path = "/fibonacciNumber", method = RequestMethod.POST)
    public String fibonacciNumber (HttpSession session, HttpServletRequest request, int input) throws Exception {
        findFibonacci(input);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping (path = "/palindromeButton", method = RequestMethod.POST)
    public String palindromeButton (HttpSession session, HttpServletRequest request, String input) throws Exception {
        findPalindrome(input);
        return "redirect:" + request.getHeader("Referer");
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
        int nth = 0;
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
        String finalInput = input.replaceAll("\\s+","");
        StringBuilder reverseBuilder = new StringBuilder();
        reverseBuilder.append(finalInput);
        reverseBuilder = reverseBuilder.reverse();
        String reverse = String.valueOf(reverseBuilder);
        Boolean isPalindrome = finalInput.equals(reverse);
        Palindrome palindrome = new Palindrome(input, reverse, isPalindrome);
        palindromes.save(palindrome);
    }


}
