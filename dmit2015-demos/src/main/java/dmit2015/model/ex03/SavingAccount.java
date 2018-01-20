package dmit2015.model.ex03;

public class SavingAccount extends BankAccount {

	@Override
	public void withdraw(double amount) {
		if (amount > 0) {
			balance -= amount;
		}

	}

}
