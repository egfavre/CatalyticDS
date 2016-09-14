package com.egfavre.controllers;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by user on 9/13/16.
 */

@Controller
public class CatalyticDSController {

//        @Autowired
//        FactorRepository factors;
//
//        @Autowired
//        PalindromeRepository palindromes;
//
//        @Autowired
//        FibonacciRepository fibonaccis;

        //create server connection
        @PostConstruct
        public void init() throws SQLException {
            Server.createWebServer().start();
        }
}
