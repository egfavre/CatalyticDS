package com.egfavre;

import com.egfavre.controllers.CatalyticDSController;
import com.egfavre.services.FactorRepository;
import com.egfavre.services.FibonacciRepository;
import com.egfavre.services.PalindromeRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalyticDsApplicationTests {
	@Autowired
	FactorRepository factors;

	@Autowired
	FibonacciRepository fibonaccis;

	@Autowired
	PalindromeRepository palindromes;

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void factorUnitTest (){
	}

}
