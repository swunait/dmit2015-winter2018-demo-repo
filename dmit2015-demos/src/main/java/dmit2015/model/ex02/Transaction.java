package dmit2015.model.ex02;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
	
	private LocalDate date;		// +getter +setter
	private char type;			// +getter +setter
	private BigDecimal amount;	// +getter +setter
	private BigDecimal balance;	// +getter +setter
	private String description;	// +getter +setter
	
	public Transaction(char type, BigDecimal amount, BigDecimal balance, String description) {
		this.date = LocalDate.now();
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "\nTransaction [date=" + date + ", type=" + type + ", amount=" + amount + ", balance=" + balance
				+ ", description=" + description + "]";
	}
	
	

}
