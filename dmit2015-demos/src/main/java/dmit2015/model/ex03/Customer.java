package dmit2015.model.ex03;

import java.util.UUID;

public class Customer {

	private String id = UUID.randomUUID().toString();		// +getter 
	private BankAccount savingAccount;						// +getter
	private BankAccount checkingAccount;					// +getter
	
	public String getId() {
		return id;
	}
	public BankAccount getSavingAccount() {
		return savingAccount;
	}
	public BankAccount getCheckingAccount() {
		return checkingAccount;
	}
	
	public void addSavingAccount() {
		savingAccount = new SavingAccount();
		savingAccount.owner = this;
	}
	
	public void addCheckingAccount() {
		checkingAccount = new CheckingAccount();
		checkingAccount.owner = this;
	}
	
}
