package dmit2015.model.ex02;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void testAllMethods() {
		// Construct a new BankAccount object
		BankAccount account1 = new BankAccount(UUID.randomUUID(), 
				BigDecimal.valueOf(100), "John Doe");
		// Deposit $1000
		account1.deposit(1000, "Money from mom.");
		// Balance should be $1100
		assertEquals(1100, account1.getBalance().doubleValue(), 0);
		// Withdraw $300
		account1.withdraw(300, "Gift for mom.");
		// Balance should be $800
		assertEquals(800, account1.getBalance().doubleValue(), 0);
		// Withdraw $200
		account1.withdraw(200, "Gift for myself.");
		// Balance should be $600
		assertEquals(600, account1.getBalance().doubleValue(), 0);
		// Print account info to screen including all transactions
		System.out.println(account1);
	}

}
