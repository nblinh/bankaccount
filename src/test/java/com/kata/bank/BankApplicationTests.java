package com.kata.bank;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kata.bank.model.Bank;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {
	Logger logger = LoggerFactory.getLogger(BankApplicationTests.class);

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void concurentTest() {
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<1000;i++) {
			threads.add(new Thread(() -> {
				Bank.accounts.get(1).deposit(10);;
			}));
			threads.add(new Thread(() -> {
				Bank.accounts.get(1).withdraw(10);;
			}));
		}
		for(Thread thread : threads) {
			thread.start();
		}
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Assert.assertEquals(0.0, Bank.accounts.get(1).getBalance(), 0.0001);
	}

}

