package com.kata.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private int id;//account number
	private double balance;
	private List<History> history=new ArrayList<>();
	
	public Account(int id) {
		this.id=id;
	}
	
	public synchronized void deposit(double amount) {
		this.balance+=amount;
		history.add(new History("deposit", amount, this.balance));
	}
	
	public synchronized void withdraw(double amount) {
		this.balance-=amount;
		history.add(new History("withdraw", amount, this.balance));
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public List<History> getHistory() {
		return history;
	}
}
