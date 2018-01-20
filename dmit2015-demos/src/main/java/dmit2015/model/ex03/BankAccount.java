package dmit2015.model.ex03;

import java.util.UUID;

public abstract class BankAccount {

	protected String accountNo = UUID.randomUUID().toString();	// +getter
	protected Customer owner;										// +getter 
	protected double balance;										// +getter
	
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		}
	}
	
	public abstract void withdraw(double amount) throws InsufficientFundsException;

	public String getAccountNo() {
		return accountNo;
	}

	public Customer getOwner() {
		return owner;
	}

	public double getBalance() {
		return balance;
	}
	
}
