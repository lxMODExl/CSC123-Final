public class CheckingAccount extends Account {

	private double overdraftLimit = 0;
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
	
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	
	public void setOverdraftLimit(double limit) {
		overdraftLimit = limit;
	}
}