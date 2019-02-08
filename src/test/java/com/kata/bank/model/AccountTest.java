package com.kata.bank.model;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

	@Test
	public void depositTest() {
		Account account = new Account(1);
		account.deposit(30);
		Assert.assertEquals(30.0, account.getBalance(), 0.01);
	}
	
	@Test
	public void withdrawTest() {
		Account account = new Account(1);
		account.deposit(30);
		account.withdraw(30);
		Assert.assertEquals(0.0, account.getBalance(), 0.01);
	}

	
}
