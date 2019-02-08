package com.kata.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kata.bank.model.Account;
import com.kata.bank.model.AccountDto;
import com.kata.bank.model.Bank;
import com.kata.bank.model.History;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class AccountController {
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@ApiOperation(value = " Deposit money. Test with bank account id = 1")
	@PutMapping("/account/deposit")
	public Account deposit(@RequestBody AccountDto accountDto) {
		Account account = Bank.accounts.get(accountDto.getId());
		if(account==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account number Not Found");
		}
		account.deposit(accountDto.getAmount());
		logger.info("account "+account.getId()+" deposit "+accountDto.getAmount());
		
		return account;
	}
	
	@ApiOperation(value = "Withdraw money. Test with bank account id = 1")
	@PutMapping("/account/withdraw")
	public Account withdraw(@RequestBody AccountDto accountDto) {
		Account account = Bank.accounts.get(accountDto.getId());
		if(account==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account number Not Found");
		}
		account.withdraw(accountDto.getAmount());
		logger.info("account "+account.getId()+" withdraw "+accountDto.getAmount());
		return account;
	}
	
	@ApiOperation(value = "Get history of account. Test with bank account id = 1")
	@GetMapping("/account/{id}/history")
	public List<History> history(@PathVariable(value = "id") int id) {
		Account account = Bank.accounts.get(id);
		if(account==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account number Not Found");
		}
		return account.getHistory();
	}

}
