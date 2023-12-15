import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {

	Scanner input = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("0.00"); 
	
	int menuNav, numInput, accNum = 1000;
	double moneyInput;
	String strInput, fName, lName, ssn, dob, name;
	AccountHolder accHolder = new AccountHolder();
	
	Map<Integer, CheckingAccount> checkingAccs = new HashMap<Integer, CheckingAccount>();
	Map<Integer, SavingAccount> savingAccs = new HashMap<Integer, SavingAccount>();
	ArrayList<String> accounts = new ArrayList<String>();

	
	public void openAccount(String type) throws Exception {
		//Checking
		switch (type) {
			case "Checking":
				System.out.println("\nEnter first name: ");
				fName = input.nextLine();

				System.out.println("\nEnter last name: ");
				lName = input.nextLine();

				System.out.println("\nEnter social security number: ");
				ssn = input.nextLine();

				System.out.println("\nEnter date of birth: ");
				dob = input.nextLine();
				
				if (accHolder.getAgeInYears(dob) >= 16) {
					CheckingAccount userChecking = new CheckingAccount();
					
					if (accHolder.getAgeInYears(dob) >= 18) {
						System.out.println("\nEnter overdraft limit: ");
						moneyInput = input.nextDouble();
						
						userChecking.setOverdraftLimit(moneyInput);
					}
										
					userChecking.setAccountHolder(fName, lName, dob, ssn);
					checkingAccs.put(accNum, userChecking);
					accounts.add(accNum +"(Checking) : "+ fName +" : "+ lName +" : "+ ssn);
					
					System.out.println("Thank you, the account number is: "+ accNum);
					accNum++;

				}else if (accHolder.getAgeInYears(dob) < 16) {
					System.out.println("You must be over 16 to open a checking account.");
				}
				break;
			case "Saving":
				System.out.println("\nEnter first name: ");
				fName = input.nextLine();
				
				System.out.println("\nEnter last name: ");
				lName = input.nextLine();
				
				System.out.println("\nEnter social security number: ");
				ssn = input.nextLine();
				
				System.out.println("\nEnter date of birth: ");
				dob = input.nextLine();
				
				name = fName +" "+ lName;
				
				if (accHolder.getAgeInYears(dob) < 5) {
					System.out.println("You must be over 5 to open a savings account.");
					break;
				}
				
				SavingAccount userSaving = new SavingAccount();
				
				userSaving.setAccountHolder(fName, lName, dob, ssn);
				savingAccs.put(accNum, userSaving);
				accounts.add(accNum +"(Checking) : "+ fName +" : "+ lName +" : "+ ssn);
				
				System.out.println("Thank you, the account number is: "+ accNum);
				accNum++;
				break;
		}
	}
	
	public void closeAccount() {
		System.out.println("Enter account number to close: ");
		numInput = input.nextInt();
		
		if (checkingAccs.containsKey(numInput)) {
			checkingAccs.get(numInput).close();
			System.out.println("Account closed, current balance is: "+ df.format(checkingAccs.get(numInput).getBalance()));
			
		} else if (savingAccs.containsKey(numInput)) {
			checkingAccs.get(numInput).close();
			System.out.println("Account closed, current balance is: "+ df.format(savingAccs.get(numInput).getBalance()));
			
		}else {
			System.out.println("Account not found");
		}
	}
	
	public void deposit() {
		System.out.println("Enter account number: ");
		numInput = input.nextInt();
		
		if (checkingAccs.containsKey(numInput)) {
			System.out.println("Enter the amount to deposit: ");
			moneyInput = input.nextDouble();
			
			if (checkingAccs.get(numInput).isOpen()) {
				checkingAccs.get(numInput).deposit(moneyInput);
				System.out.println("Deposit successful, the new balance is: "+ checkingAccs.get(numInput).getBalance());
				
			}else if (!(checkingAccs.get(numInput).isOpen()) && (checkingAccs.get(numInput).getBalance() + moneyInput) <= 0) {
				checkingAccs.get(numInput).deposit(moneyInput);
				System.out.println("Deposit successful, the new balance is: "+ checkingAccs.get(numInput).getBalance());
			}
			else {
				System.out.println("Deposit failed, the balance is: "+ df.format(checkingAccs.get(numInput).getBalance()));
			}
		} else if (savingAccs.containsKey(numInput)) {
			System.out.println("Enter the amount to deposit: ");
			moneyInput = input.nextDouble();
			
			if (savingAccs.get(numInput).isOpen()) {
				savingAccs.get(numInput).deposit(moneyInput);
				System.out.println("Deposit successful, the new balance is: "+ df.format(savingAccs.get(numInput).getBalance()));
				
			}else {
				System.out.println("Deposit failed, the balance is: "+ df.format(savingAccs.get(numInput).getBalance()));
			}
			
		}else {
			System.out.println("Account not found");
		}
	}
	
	public void withdraw() {
		System.out.println("Enter account number: ");
		numInput = input.nextInt();
		
		if (checkingAccs.containsKey(numInput)) {
			System.out.println("Enter the withdrawal amount: ");
			moneyInput = input.nextDouble();
			
			if ((checkingAccs.get(numInput).getBalance() - moneyInput) >= 0 - checkingAccs.get(numInput).getOverdraftLimit()) {
				checkingAccs.get(numInput).withdraw(moneyInput);
				System.out.println("Withdrawal successful, the new balance is: "+ df.format(checkingAccs.get(numInput).getBalance()));
				
			}else if (!(checkingAccs.get(numInput).isOpen()) && checkingAccs.get(numInput).getBalance() - moneyInput >= 0 - checkingAccs.get(numInput).getOverdraftLimit()) {
				checkingAccs.get(numInput).withdraw(moneyInput);
				System.out.println("Withdrawal successful, the new balance is: "+ df.format(checkingAccs.get(numInput).getBalance()));
				
			}else {
				System.out.println("Withdrawal failed, the balance is: "+ df.format(checkingAccs.get(numInput).getBalance()));
			}
			
		} else if (savingAccs.containsKey(numInput)) {
			System.out.println("Enter the amount to withdraw: ");
			moneyInput = input.nextDouble();
			
			if (savingAccs.get(numInput).isOpen() && savingAccs.get(numInput).getBalance() - moneyInput >= 0) {
				savingAccs.get(numInput).withdraw(moneyInput);
				System.out.println("Withdrawal successful, the new balance is: "+ df.format(savingAccs.get(numInput).getBalance()));
				
			}else if (!(savingAccs.get(numInput).isOpen()) && savingAccs.get(numInput).getBalance() - moneyInput >= 0) {
				savingAccs.get(numInput).withdraw(moneyInput);
				System.out.println("Withdrawal successful, the new balance is: "+ df.format(savingAccs.get(numInput).getBalance()));
				
			}else {
				System.out.println("Withdrawal failed, the balance is: "+ df.format(savingAccs.get(numInput).getBalance()));
			}
			
		}else {
			System.out.println("Account not found");
		}	
	}
	
	public void listAccounts() {
		System.out.println("\n<Account Number> (<Account Name>) : <First Name> : <Last Name> : <SSN> : <Balance> : <Account Status>\n");
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i) +" : ");
			
			if (checkingAccs.containsKey(i + 1000)) {
				System.out.print(df.format(checkingAccs.get(i + 1000).getBalance()) +" : ");
				
				if (checkingAccs.get(i + 1000).isOpen()) {
					System.out.println("Account Open");
					
				}else {
					System.out.println("Account Closed");
				}
				
			} else if (savingAccs.containsKey(i + 1000)) {
				System.out.print(df.format(savingAccs.get(i+ 1000).getBalance()) +" : ");
				
				if (savingAccs.get(i + 1000).isOpen()) {
					System.out.println("Account Open");
					
				}else {
					System.out.println("Account Closed");
				}
			}
		}
	}
	
	public void accountStatement() {
		System.out.println("Enter account number: ");
		numInput = input.nextInt();
		
		if (checkingAccs.containsKey(numInput)) {
			checkingAccs.get(numInput).getStatement();
			
		} else if (savingAccs.containsKey(numInput)) {
			savingAccs.get(numInput).getStatement();
			
		}else {
			System.out.println("Account not found");
		}	
	}
}