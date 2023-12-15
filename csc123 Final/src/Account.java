import java.util.ArrayList;

public class Account {

	ArrayList<String> transactions = new ArrayList<String>();
	
	DebitTransaction debit = new DebitTransaction();
	CreditTransaction credit = new CreditTransaction();
	AccountHolder accountHolder = new AccountHolder();
	
	private boolean status = true;
	private int transCount = 1;

	public Account() {
		
	}
	
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
	
	public double getBalance() {
		
		double balance = credit.getAmount() - debit.getAmount();	
		return balance;
	}
	
	public boolean isOpen() {
		return status;
	}
	
	public void close() {
		status = false;
		System.out.println("Account closed, current balance is: "+ getBalance());
	}

	public String getAccountHolder() {
		return accountHolder.getName();
	}
	
	public void setAccountHolder(String fName, String lName, String dob, String ssn) {
		accountHolder = new AccountHolder(fName+" "+lName, dob, ssn);
	}
	
	public void getStatement() {
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println(transactions.get(i));
		}
		
		System.out.println("\nBalance: "+ getBalance());
	}
}