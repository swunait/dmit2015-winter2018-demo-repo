package dmit2015.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void testGetDateCreated() {
		// Create a BankAccount object with an balance of $1000
		BankAccount account1 = new BankAccount();
		account1.setBalance(BigDecimal.valueOf(1000));
		// Set the annual interest rate to 1.5%
		account1.setAnnualInterestRate(1.5);
		// The monthly interest rate should be 0.00125
		assertEquals(0.00125, account1.getMonthlyInterestRate(), 0);
		// The monthly interest amount should be $1.25
		assertEquals(1.25, account1.getMonthlyInterest().doubleValue(), 0);
		// Withdraw $1500
		account1.withdraw(1500);
		// The balance should be $1000
		assertEquals(1000.00, account1.getBalance().doubleValue(), 0);
		// Withdraw $200
		account1.withdraw(200);
		// The balance should be $800
		assertEquals(800.00, account1.getBalance().doubleValue(), 0);
		// Deposit $1200
		account1.deposit(1200);
		// The balance should be $2000.00
		assertEquals(2000.00, account1.getBalance().doubleValue(), 0);
		// Add the monthly interest to the balance
		account1.addMonthlyInterest();
		// The balance should be $2002.50
		assertEquals(2002.50, account1.getBalance().doubleValue(), 0);
		
		// Print all account info to the screen
		System.out.println(account1);
	}

}
