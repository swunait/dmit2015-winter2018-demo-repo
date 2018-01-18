package dmit2015.model.ex02;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

	private UUID id = UUID.randomUUID();				// +getter +setter
		
	private BigDecimal balance = BigDecimal.ZERO;		// +getter +setter
		
	private double annualInterestRate = 0.00;			// +getter +setter
		
	private LocalDate dateCreated = LocalDate.now();	// +getter
	
	private String name;	// +getter +setter
	
	private List<Transaction> transactions = new ArrayList<>();	// +getter

	public BankAccount() {
	}

	public BankAccount(UUID id, BigDecimal balance) {
		this.id = id;
		this.balance = balance.setScale(2,RoundingMode.HALF_UP);
	}

	public BankAccount(UUID id, BigDecimal balance, String name) {
		this.id = id;
		this.balance = balance;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		if (annualInterestRate >= 0)
			this.annualInterestRate = annualInterestRate;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public double getMonthlyInterestRate() {
		return annualInterestRate / 100 / 12;
	}
	
	public BigDecimal getMonthlyInterest() {
		BigDecimal monthlyInterest = BigDecimal.valueOf( getMonthlyInterestRate() * balance.doubleValue() );
		return monthlyInterest.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal addMonthlyInterest() {
		balance = balance.add(getMonthlyInterest());
		return balance;
	}
	
	public void withdraw(double amount, String description) {
		withdraw(BigDecimal.valueOf(amount), description);
	}
	public void withdraw(BigDecimal amount, String description) {
		if (amount.doubleValue() > 0) {
			if (amount.doubleValue() <= balance.doubleValue()) {
				balance = balance.subtract(amount);		
				// Construct a new Transaction object
				Transaction currentTransaction = new Transaction('W', amount, balance, description);
				// Add the currentTransaction to the list of transactions
				transactions.add(currentTransaction);
			}
		}
	}
	
	public void deposit(double amount, String description) {
		deposit(BigDecimal.valueOf(amount), description);
	}
	public void deposit(BigDecimal amount, String description) {
		if (amount.doubleValue() > 0) {
			balance = balance.add(amount);
			// Construct a new Transaction object
			Transaction currentTransaction = new Transaction('D', amount, balance, description);
			// Add the currentTransaction to the list of transactions
			transactions.add(currentTransaction);
		}
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", balance=" + balance + ", annualInterestRate=" + annualInterestRate
				+ ", dateCreated=" + dateCreated + ", name=" + name + ", transactions=" + transactions + "]";
	}
	
	
}
