import java.util.Scanner;

public class BankApp {

	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		int menuNav;
		Bank account = new Bank();
		
		do {
			do {
				System.out.println("\n1 - Open a Checking Account\n"
								 + "2 - Open a Saving Account\n"
								 + "3 - List Accounts\n"
								 + "4 - Account Statment\n"
								 + "5 - Deposit funds\n"
								 + "6 - Withdraw funds\n"
								 + "7 - Close an account\n"
								 + "8 - Exit\n"
								 + "\nPlease enter you choice: ");
				menuNav = input.nextInt();
			} while(menuNav < 1 || menuNav > 8);
			
			switch(menuNav) {
				case 1:
					account.openAccount("Checking");
					break;
				case 2:
					account.openAccount("Saving");
					break;
				case 3:
					account.listAccounts();
					break;
				case 4:
					account.accountStatement();
					break;
				case 5:
					account.deposit();
					break;
				case 6:
					account.withdraw();
					break;
				case 7:
					account.closeAccount();
					break;
				case 8:
					System.exit(0);
					break;
			}
		} while(menuNav > 1 || menuNav < 8);
	}
}