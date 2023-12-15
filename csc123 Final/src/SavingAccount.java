public class SavingAccount extends Account {
	
	private int transCount = 1;
	
	public void deposit(double amount) {
		credit.amount.add(amount);
		transactions.add(transCount +" : Credit : "+ amount);
		transCount++;
	}
	
	public void withdraw(double amount) {
		debit.amount.add(amount);
		transactions.add(transCount +" : Debit : "+ amount);
		transCount++;
	}
}