package com.kata.bank.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.kata.bank.model.Account;
import com.kata.bank.model.Bank;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
	
	public void resetBankAccout() {
		Bank.accounts.put(1, new Account(1));
	}
	
	
	@Test
	public void test1_Deposit() throws Exception {
		resetBankAccout();
		
		mvc.perform(MockMvcRequestBuilders.put("/api/account/deposit")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createAccountDtoJson(1, 30)))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void test2_Withdraw() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/api/account/withdraw")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createAccountDtoJson(1, 30)))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void test3_GetHistory() throws Exception {
	     
	    mvc.perform(MockMvcRequestBuilders.get("/api/account/1/history")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
	      .andExpect(MockMvcResultMatchers.jsonPath("$[0].operation", is("deposit")))
	      .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance", is(30.0)))
	      .andExpect(MockMvcResultMatchers.jsonPath("$[1].operation", is("withdraw")))
	      .andExpect(MockMvcResultMatchers.jsonPath("$[1].balance", is(0.0)));
	}
	
	private String createAccountDtoJson (int id, double amount) {
        return "{ \"id\": \"" + id + "\", " +
                            "\"amount\":" + amount + "}";
    }

}
