package dmit2015.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import dmit2015.model.ex02.BankAccount;

@Named("currentBankAccountController")
@SessionScoped
public class BankAccountController implements Serializable {
	private static final long serialVersionUID = 1L;

	private BankAccount currentBankAccount = new BankAccount();	// +getter +setter
	private Double amount;										// +getter +setter
	private String description;
	
	public void doWithdraw(ActionEvent event) {
		currentBankAccount.withdraw(amount, description);
		amount = null;
		description = null;		
	}

	public void doDeposit(ActionEvent event) {
		currentBankAccount.deposit(amount, description);
		amount = null;
		description = null;		
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BankAccount getCurrentBankAccount() {
		return currentBankAccount;
	}
}
