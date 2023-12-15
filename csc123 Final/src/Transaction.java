import java.util.ArrayList;

public class Transaction {
	ArrayList<Double> amount = new ArrayList<Double>();
	
	public double getAmount() {
		double balance = 0;
		
		for(int t = 0; t < amount.size(); t++) {
			balance += amount.get(t);
		}
		return balance;
	}
	
	public double getTransaction(int i) {
		return amount.get(i);
	}
}