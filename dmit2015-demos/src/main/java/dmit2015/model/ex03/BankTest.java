package dmit2015.model.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test(expected=InsufficientFundsException.class)
	public void testGetAccounts() throws InsufficientFundsException {
		// Get an instance of the singleton Bank object
		Bank myBank = Bank.getInstance();
		// Create a Customer object and give it a saving account
		Customer customer1 = new Customer();
		customer1.addSavingAccount();
		// Add customer1 to the Bank
		myBank.addCustomer(customer1);
		// Add customer1 savingAccount to the Bank's accounts
		myBank.addAccount(customer1.getSavingAccount());
		// Deposit $100 to the saving account
		customer1.getSavingAccount().deposit(100);
		// Withdraw $20 from the saving account
		customer1.getSavingAccount().withdraw(20);
		// The balance should be $80
		assertEquals(80, customer1.getSavingAccount().getBalance(), 0);
		// Add checking account for customer1 and deposit $100
		customer1.addCheckingAccount();
		myBank.addAccount(customer1.getCheckingAccount());
		customer1.getCheckingAccount().deposit(100);
		// Withdraw $120 from checking account
		customer1.getCheckingAccount().withdraw(120);
		// savingAccount balance should be $60
		assertEquals(60, customer1.getSavingAccount().getBalance(), 0);
		// checkingAccount balance should be 0
		assertEquals(0, customer1.getCheckingAccount().getBalance(), 0);
		// Try to withdraw $80 from the checking account
		customer1.getCheckingAccount().withdraw(80);
		// Should throw an InsufficientFundsException
		
	}

}
