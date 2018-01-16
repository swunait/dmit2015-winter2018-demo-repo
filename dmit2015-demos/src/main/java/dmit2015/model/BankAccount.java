package dmit2015.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

public class BankAccount {

	private UUID id = UUID.randomUUID();				// +getter +setter
		
	private BigDecimal balance = BigDecimal.ZERO;		// +getter +setter
		
	private double annualInterestRate = 0.00;			// +getter +setter
		
	private LocalDate dateCreated = LocalDate.now();	// +getter

	public BankAccount() {
	}

	public BankAccount(UUID id, BigDecimal balance) {
		this.id = id;
		this.balance = balance.setScale(2,RoundingMode.HALF_UP);
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
	
	public void withdraw(double amount) {
		withdraw(BigDecimal.valueOf(amount));
	}
	public void withdraw(BigDecimal amount) {
		if (amount.doubleValue() > 0) {
			if (amount.doubleValue() <= balance.doubleValue()) {
				balance = balance.subtract(amount);				
			}
		}
	}
	
	public void deposit(double amount) {
		deposit(BigDecimal.valueOf(amount));
	}
	public void deposit(BigDecimal amount) {
		if (amount.doubleValue() > 0) {
			balance = balance.add(amount);
		}
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", balance=" + balance + ", annualInterestRate=" + annualInterestRate
				+ ", dateCreated=" + dateCreated + "]";
	}
	
	
}
