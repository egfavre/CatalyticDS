package com.egfavre;

import com.egfavre.CatalyticDsApplication;
import com.egfavre.entities.Factor;
import com.egfavre.entities.Fibonacci;
import com.egfavre.entities.Palindrome;
import com.egfavre.services.FactorRepository;
import com.egfavre.services.FibonacciRepository;
import com.egfavre.services.PalindromeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by user on 9/16/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CatalyticDsApplication.class)
@WebAppConfiguration
public class CatalyticDsApplicationTests {
	@Autowired
	WebApplicationContext wac;

	@Autowired
	FactorRepository factors;

	@Autowired
	FibonacciRepository fibonaccis;

	@Autowired
	PalindromeRepository palindromes;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}


	@Test
	public void factorPage() throws Exception {
		Factor factor = new Factor(2, "1,2");
		factors.save(factor);

		ResultActions ra = mockMvc.perform(
				MockMvcRequestBuilders.get("/factor")
		);
		MvcResult result = ra.andReturn();
		MockHttpServletResponse response = result.getResponse();
		String json = response.getContentAsString();

		Assert.assertTrue(json.contains("2"));
	}

	@Test
	public void fibonacciPage() throws Exception {
		Fibonacci fibonacci = new Fibonacci(3, 1);
		fibonaccis.save(fibonacci);

		ResultActions ra = mockMvc.perform(
				MockMvcRequestBuilders.get("/fibonacci")
		);
		MvcResult result = ra.andReturn();
		MockHttpServletResponse response = result.getResponse();
		String json = response.getContentAsString();

		Assert.assertTrue(json.contains("3"));
	}

	@Test
	public void palindromePage() throws Exception {
		String input = "race car";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/palindromeButton")
						.param("input", input)
		);

		Assert.assertTrue(palindromes.findByInput("race car") != null);
	}

	@Test
	public void factorNumber() throws Exception {

	}

	@Test
	public void fibonacciNumber() throws Exception {

	}

	@Test
	public void palindromeButton() throws Exception {
		String input = "race car";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/palindromeButton")
						.param("input", input)
		);

		Assert.assertTrue(palindromes.findByInput("race car") != null);
	}

	@Test
	public void findFactors() throws Exception {
	}

	@Test
	public void findFibonacci() throws Exception {

	}

	@Test
	public void findPalindrome() throws Exception {

	}

}