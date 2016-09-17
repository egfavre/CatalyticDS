package com.egfavre.controllers;

import com.egfavre.entities.Factor;
import com.egfavre.entities.Fibonacci;
import com.egfavre.entities.Palindrome;
import com.egfavre.services.FactorRepository;
import com.egfavre.services.FibonacciRepository;
import com.egfavre.services.PalindromeRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.InfoProperties;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String home () {
        return "home";
    }

    @RequestMapping (path = "/factor", method = RequestMethod.GET)
    public String factorPage (Model model) {
        Iterable<Factor> factorlist;
        factorlist = factors.findAll();
        model.addAttribute("factorList", factorlist);
        return "factor";
    }

    @RequestMapping (path = "/fibonacci", method = RequestMethod.GET)
    public String fibonacciPage (Model model) {
        Iterable<Fibonacci> fibonacciList = fibonaccis.findAll();
        ArrayList<Integer> inputValues = new ArrayList<>();
        for (Fibonacci fib : fibonacciList) {
            if (!inputValues.contains(fib.getInput())) {
                inputValues.add(fib.getInput());
            }
        }
        HashMap<Integer, ArrayList> finalFibSeqs = new HashMap<>();
        for (Integer inputValue:inputValues) {
            Iterable<Fibonacci> fib = fibonaccis.findByInput(inputValue);
            ArrayList<Integer> fibSeq = new ArrayList<>();
            for (Fibonacci thisFib:fib) {
                fibSeq.add(thisFib.getAnswer());
            }
            finalFibSeqs.put(inputValue, fibSeq);
        }
        int mostRecent = Integer.valueOf(String.valueOf(fibonaccis.count()));
        model.addAttribute("fibonacciList", fibonacciList);
        model.addAttribute("finalFibSeqs", finalFibSeqs);
        return "fibonacci";
    }

    @RequestMapping (path = "/palindrome", method = RequestMethod.GET)
    public String palindromePage (Model model) {
        Iterable<Palindrome> palindromeList;
        palindromeList = palindromes.findAll();
        model.addAttribute("palindromeList", palindromeList);
        return "palindrome";
    }

    @RequestMapping (path = "/factorNumber", method = RequestMethod.POST)
    public String factorNumber (HttpServletRequest request, int input) throws Exception {
        findFactors(input);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping (path = "/fibonacciNumber", method = RequestMethod.POST)
    public String fibonacciNumber (HttpServletRequest request, int input) throws Exception {
        findFibonacci(input);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping (path = "/palindromeButton", method = RequestMethod.POST)
    public String palindromeButton (HttpServletRequest request, String input) throws Exception {
        findPalindrome(input);
        return "redirect:" + request.getHeader("Referer");
    }

    //find factors of the input
    public void findFactors (int input){
        ArrayList<Integer> factorResultsList = new ArrayList<Integer>();
        int start = 1;
        while (start <= input){
            if (input % start == 0){
                factorResultsList.add(start);
            }
            start++;
        }
        String factorResults = factorResultsList.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        Factor factor = new Factor(input, factorResults);
        factors.save(factor);
    }

    //find the nth value of the standard fibonacci sequence
    public void findFibonacci (int input){
        int nth = 0;
        int a = 0;
        int b = 1;
        Fibonacci fibonacciA= new Fibonacci(input, a);
        Fibonacci fibonacciB = new Fibonacci(input, b);
        fibonaccis.save(fibonacciA);
        fibonaccis.save(fibonacciB);
        while (input >= nth){
            int c = a + b;
            a = b;
            b = c;
            Fibonacci fibonacci = new Fibonacci(input, c);
            fibonaccis.save(fibonacci);
            nth++;
        }
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
