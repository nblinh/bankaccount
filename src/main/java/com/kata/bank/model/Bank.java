package com.kata.bank.model;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	public static Map<Integer, Account> accounts = new HashMap<>();
	
	static {
		accounts.put(1, new Account(1));
	}
}
