package dmit2015.model.ex03;

import java.util.HashMap;
import java.util.Map;

public class Bank {

	private Bank() { 		
	}
	
	private static final Bank INSTANCE = new Bank();
	
	public static Bank getInstance() {
		return INSTANCE;
	}
	
	private Map<String, Customer> customers = new HashMap<>();		// +getter
	private Map<String, BankAccount> accounts = new HashMap<>();	// +getter
	
	public void addCustomer(Customer newCustomer) {
		customers.put(newCustomer.getId(), newCustomer);
	}
	
	public void addAccount(BankAccount newAccount) {
		accounts.put(newAccount.getAccountNo(), newAccount);
	}

	public Map<String, Customer> getCustomers() {
		return customers;
	}

	public Map<String, BankAccount> getAccounts() {
		return accounts;
	}
	
}
