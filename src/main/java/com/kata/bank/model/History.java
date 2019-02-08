package com.kata.bank.model;

import java.time.LocalDateTime;

public class History {
	private String operation;
	private LocalDateTime date;
	private double amount;
	private double balance;
	
	public History(String operation, double amount, double balance){
		this.operation=operation;
		this.amount=amount;
		this.balance=balance;
		this.date=LocalDateTime.now();
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
