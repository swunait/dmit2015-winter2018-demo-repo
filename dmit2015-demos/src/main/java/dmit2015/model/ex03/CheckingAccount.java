package dmit2015.model.ex03;

public class CheckingAccount extends BankAccount {

	@Override
	public void withdraw(double amount) throws InsufficientFundsException {
		if (amount <= balance ) {
			balance -= amount;
		} else {
			if (owner.getSavingAccount() != null) {
				double overAmount = amount - balance;
				// check if there is enough funds in the saving account to cover the overAmount
				if (owner.getSavingAccount().getBalance() >= overAmount) {
					// transfer funds from saving to checking
					owner.getSavingAccount().withdraw(overAmount);
					deposit(overAmount);
					balance -= amount;
				} else {
					throw new InsufficientFundsException("Not enough funds from savings to transfer for withdraw");
				}
				
			} else {
				throw new InsufficientFundsException("You don't have enough funds to withdraw");
			}
		}
		
	}

}
