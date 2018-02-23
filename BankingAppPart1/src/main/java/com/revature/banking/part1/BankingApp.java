package com.revature.banking.part1;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class BankingApp implements InterfaceBankDriver, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4602018659434205045L;
	private Bank bank;
	public static void main(String[] args) {
		Bank bank = BankingApp.loadBank();
		BankingApp banking = new BankingApp(bank);
		Scanner scan = new Scanner(System.in);
		banking.welcome(scan);		
	}
	public BankingApp(Bank bank) {
		super();
		this.bank = bank;
	}

	public static Bank loadBank() {
		Bank bank = DataManager.getBank();
		if(bank == null) {
			bank = new Bank();
			DataManager.saveBankData(bank);
		}
		return bank;
	}
	public static Bank loadNewBank() {
		//Bank bank = DataManager.getBank();
		Bank bank = new Bank();
		DataManager.saveBankData(bank);
		return bank;
	}
	public void welcome(Scanner scan) {
//		List<Customer> customers = bank.getCustomers();
//		for(Customer customer: customers) {
//			System.out.println(customer.toString());
//		}
//		List<Account> accounts = bank.getAccounts();
//		for(Account account: accounts)
//			System.out.println(account.toString());
		System.out.println("Welcome to the Bank!! \nSelect one of the following to continue:\n"
				+ "1:\tLogin\n2:\tRegister\n3:\tExit");
		
		int choice = this.getNumber(scan);
		if(choice ==1) {
			this.login(scan);
		}else if (choice ==2) {
			this.home(this.registerCustomer(scan), scan);
		}else if(choice == 3) {
			scan.close();
			DataManager.saveBankData(bank);
			System.exit(0);
		}else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.welcome(scan);
		}
		this.welcome(scan);
	}
	
	public Customer registerCustomer(Scanner scan) {
		System.out.println("Thank you for your interest in banking with us."
				+ "Enter the following infomation to create your login credentials");
		String username = this.getUsername(scan);
		if(username.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String password = this.getPassword(scan);
		if(password.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String name = this.getFullName(scan);
		if(name.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String address = this.getAddress(scan);
		if(address.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String phone = this.getPhone(scan);
		if(phone.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		Customer customer = bank.registerCustomer(username, password, name, address, phone);
		System.out.println("Thank you, " + customer.getName() + ". The registration is Complete.\n"
				+ "Use the following username for system access.\n"
				+ "Username:\t"  + customer.getUsername() + "\nPassword\t" + password 
				+ "\nCustomerID:\t" + customer.getID());
		return customer;
	}
	public Employee registerEmployee(Admin admin, Scanner scan) {
		System.out.println("Please enter the following infomation to create your login credentials");
		String username=this.getUsername(scan);
		if(username.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String password = this.getPassword(scan);
		if(password.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		Employee employee =  bank.registerEmployee(username, password);
		System.out.println("Thank you, " + admin.getUsername() + ". The registration is Complete.\n"
				+ "Provide the following credentials to the employee for system access.\n"
				+ "Username:\t"  + employee.getUsername() + "\nPassword\t" + password 
				+ "\nEmployee ID:\t" + employee.getID());
		return employee;
	}
	public Admin registerAdmin(Admin admin, Scanner scan) {
		System.out.println("Enter the following infomation to create your login credentials");
		String username=this.getUsername(scan);
		if(username.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String password = this.getPassword(scan);
		if(password.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		Admin newAdmin = bank.registerAdmin(username, password);
		System.out.println("Thank you, " + admin.getUsername() + ". The registration is Complete.\n"
				+ "Provide the following credentials to the Admin for system access.\n"
				+ "Username:\t"  + newAdmin.getUsername() + "\nPassword\t" + password 
				+ "\nEmployee ID:\t" + newAdmin.getID());
		return newAdmin;
	}
	public void login(Scanner scan) {
		boolean success = false;
		System.out.println("\nLOGIN\n");
		while(!success) {
			System.out.print("Username:\t");
			String username=scan.nextLine().toLowerCase();
			if (username.equals("exit"))
				return;
			if(!bank.usernamesContains(username)) {
				System.out.println("The username was not found. Try again!!");
				return;
			}
			System.out.print("Password:\t");
			String password = scan.nextLine();
			if (password.equals("exit"))
				return;
			User user = bank.userIdsGet(username);
			if (user.checkPassword(password)) {
				success=true;
				if(user.getRole().equals("Admin")) {
						this.home((Admin) user, scan);
				}else if (user.getRole().equals("Employee")) {
						this.home((Employee) user, scan);
				}else if (user.getRole().equals("Customer")) {
						this.home((Customer) user, scan);
				}
			}else
				System.out.println("Incorrect password!! Try again");
		}
		return;
	}

	public void home(Admin admin, Scanner scan) {
		//	Approving/denying applications
		//	withdrawing, depositing, transferring from all accounts
		//	canceling accounts
		//	logout/back to main menu
		System.out.println("\nHello " + admin.toString() + "\nSelect one of the following options\n"
				+"1:\tView open applications\n2:\tView all customer information\n"
				+ "3:\tWithdraw, Transfer, or deposit money\n"
				+"4:\tCancel account\n5:\tChange login information\n6:\tRegister new employee\n"
				+ "7:\tRegister new admin\n8:\tLogout / Go back to the main menu");
		int choice = this.getNumber(scan);
		if(choice ==-1) 
			return;
		if(choice ==1) 
			this.viewApplicationsEmployee(admin, scan);
		else if (choice == 2)
			this.viewCustomerInfo(admin, scan);
		else if (choice == 3)
			this.modAccountBalance(admin, scan);
		else if (choice ==4) 
			this.cancelAccount(admin, scan);
		else if (choice == 5) 
			this.userProfile(admin, scan);
		else if (choice == 6) 
			this.registerEmployee(admin, scan);
		else if (choice == 7) 
			this.registerAdmin(admin, scan);
		else if (choice == 8) 
			this.welcome(scan);
		else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.home(admin, scan);
		}
		DataManager.saveBankData(bank);
		this.home(admin, scan);
	}

	public void home(Employee employee, Scanner scan) {
		//***ACCESS VIEW ONLY**
		//	account information
		//	Account balances
		//	Personal information
		System.out.println("\nHello " + employee.toString() + "\nSelect one of the following options\n"
				+"1:\tView open applications\n2:\tView customer information\n"
				+"3:\tChange login information\n4:\tLogout / Go back to the main menu");
		int choice =0;
		try {
			choice = Integer.parseInt(scan.nextLine().trim());
		}catch(NumberFormatException e) {
			System.out.println("");
			this.home(employee, scan);
			return;
		}	
		if(choice ==1) {
			this.viewApplicationsEmployee(employee, scan);
		}else if (choice == 2){
			//this.viewCustomerInfo((User) employee, scan);
			//need to get customer ID or username to pull up customer.
			
		}else if (choice ==3) {
			this.userProfile(employee, scan);
		}else if (choice == 4) {
			this.welcome(scan);
		}else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.home(employee, scan);
		}
		this.home(employee, scan);
	}
	public void home(Customer customer, Scanner scan){
		//	Apply for accounts including joint accounts
		//	Check on applications
		//	withdrawing, depositing, transferring from their accounts
		System.out.println("\nHello " + customer.getName() 
				+",\n" + customer.toString() +
				"\nSelect one of the following options\n"
				+"1:\tApply for a new account\n2:\tView all applications\n"
				+"3:\tView Account Information\n4:\tWithdraw, Transfer, or deposit money\n"
				+"5:\tChange login information\n6:\tLogout / Go back to the main menu\nEnter your choice:\t");
		int choice = this.getNumber(scan);		
		if(choice ==1) {
			this.customerApply(customer, scan);
		}else if (choice == 2){
			this.viewApplicationsCustomer(customer, scan);
		}else if (choice ==3) {
			//this.modAccountBalance(customer, scan);
			this.viewCustomerInfo(customer, scan);
		}else if (choice ==4) {
			this.modAccountBalance(customer, scan);
		}else if (choice == 5) {
			this.userProfile(customer, scan);
		}else if (choice == 6) {
			this.welcome(scan);
		}else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.home(customer, scan);
		}
		this.home(customer, scan);
	}
	public void userProfile(User user, Scanner scan) {
		//	update username
		//	update password
		System.out.println("\nHello, " + user.getUsername() + ", what would you like to do?\n"
				+ "1:\tChange username\n2:\tChange password\n3:\tLogout / Back to main menu");
		int choice =0;
		try {
			choice = Integer.parseInt(scan.nextLine().trim());
		}catch(NumberFormatException e) {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.userProfile(user, scan);
			return;
		}	
		if(choice ==1) {
			String username = this.getUsername(scan);
			this.bank.userIdsRemove(user.getUsername());
			this.bank.userIdsPut(username, user);
			this.bank.usernamesAdd(username);
			this.bank.usernamesRemove(user.getUsername());
			user.setUsername(username);
		}else if (choice == 2){
			String password = this.getPassword(scan);
			user.setPassword(password);
		}else if (choice !=3) {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.userProfile(user, scan);
		}
	}
	public void viewApplicationsCustomer(Customer customer, Scanner scan) {
		//print all applications with customerID
		//bank.getApplicationsByCustomerID(customer.getID())
		List<Application> customerApplications = customer.getApplications();
		if(customerApplications.size() ==0) {
			System.out.println("There are no applications at this time");
		}
		for(Application application: customerApplications) {
			System.out.println(application.getApplicationID()+ "\t"+ application.getStatus());
		}
	}
	public void viewApplicationsEmployee(Employee employee, Scanner scan) {
		// Print all applications
		if(this.bank.getNumApplications()==0) {
			System.out.println("There are no applications at this time");
			return;
		}
		List<Application> applications = bank.getApplications();
		for(int i=0; i< applications.size(); i++) {
			System.out.println((i+1) + "\t" + applications.get(i).getApplicationID()+ "\t"+ applications.get(i).getStatus());
		}
		//Select application to approve/deny
		boolean success = false;
		int choice =0;
		while (!success) {
			System.out.println("Select Application\n");
			choice =this.getNumber(scan);
			if(choice ==-1) 
				return;
			else if((choice <= 0) || choice > applications.size()+1 ) 
				System.out.println("Incorrect choice detected!!\nTry again...");
			else
				success=true;
		}
		Application select = applications.get(choice-1);
		System.out.println("What would you like to do with this application\n"
				+"1:\tApprove\n2:\tDeny");
		success = false;
		choice =0;
		while (!success) {
			System.out.println("Select Application\n");
			choice =this.getNumber(scan);
			if((choice <= 0) || choice >2 ) {
				System.out.println("Incorrect choice detected!!\nTry again...");
			}else
				success=true;
		}
		if(choice ==-1) 
			return;
		else if(choice==1) {
			bank.approveApplication(select, employee);
		}else {
			bank.denyApplication(select, employee);
		}
		DataManager.saveBankData(bank);
		if(employee instanceof Admin) {
			this.home((Admin) employee, scan);
		}
	}

	public void modAccountBalance(Admin admin, Scanner scan) {
		//bank.getAllAccounts
		//options to withdraw, transfer, deposit
		//bank."selectedTransaction"(customer selected account, other account)
		List<Account> accounts = bank.getAccounts();
		if(accounts.size()!=0) {
			System.out.println("\nWhat would you like to do?\n1:\tWithdraw\n2:\tDeposit\n"
					+ "3:\tTransfer\n4:\tExit\nEnter your choice:\t");
			boolean success = false;
			int choice =0;
			while(!success) {
				choice = this.getNumber(scan);
				if(choice ==-1) 
					return;
				else if(choice>0 || choice <5) {
					success=true;
				}else
					System.out.println("Incorrect choice detected!!\nTry again...");
			}
			if(choice ==4) {
				return;
			}else {
				for (int i=0; i< accounts.size(); i++) {
					System.out.println((i+1) +"\t"+accounts.get(i).getAccountNumber() 
							+ "\t" + accounts.get(i).getStatus() + "\t$" + accounts.get(i).getBalance());
				}
				System.out.println("Select an account number\n");
				success = false;
				int choice2 =0;
				while(!success) {
					choice2 = this.getNumber(scan);
					if(choice ==-1) 
						return;
					else if(choice2>0 && choice2 <=accounts.size() && accounts.get(choice2-1).getBalance()>0) {
						success=true;
					}else
						System.out.println("Incorrect choice detected!!\nTry again...");
				}
				Account account1 = accounts.get(choice2-1);
				if (choice == 3){
					//Transefer between accounts
					if(accounts.size()>1) {
						System.out.println("Select the account number\n");
						success = false;
						int choice3 =0;
						while(!success) {
							choice3 = this.getNumber(scan);
							if(choice ==-1) 
								return;
							else if(choice3>0 && choice3 <=accounts.size() && choice3 != choice2) {
								success=true;
							}else
								System.out.println("Incorrect choice detected!!\nTry again...");
						}
						Account account2 = accounts.get(choice3-1);
						System.out.println("Enter an amount\n");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>0 && amount <account1.getBalance()) {
								success=true;
							}else
								System.out.println("ERROR:\tamount cannot be greater than balance!!\n"
										+ "ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						this.bank.transfer(account1, account2, amount);
						System.out.println("$"+amount+" has been transferred to the account: "+account2.getAccountNumber());
						System.out.println("The new balance is for " + account1.getAccountNumber() + " is now\t$"+ account1.getBalance());
						System.out.println("The new balance is for " + account2.getAccountNumber() + " is now\t$"+ account2.getBalance());
						
					}else
						System.out.println("There are no other accounts to transer to");
				}else {
					if (choice ==1) {
						//withdraw
						System.out.println("Enter an amount\n");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>0 && amount <account1.getBalance()) {
								success=true;
							}else
								System.out.println("ERROR:\tamount cannot be greater than balance!!\n"
										+ "ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						bank.withdraw(account1, amount);
						System.out.println("$"+amount+" has been withdrawn from the account: "+account1.getAccountNumber());
						System.out.println("The new balance is for " + account1.getAccountNumber() + " is now\t"+ account1.getBalance());
					}else if (choice == 2) {
						//deposit
						System.out.println("Enter an amount\n");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>0) {
								success=true;
							}else
								System.out.println("ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						bank.deposit(account1, amount);
						System.out.println("$"+amount+" has been deposited to the account: "+account1.getAccountNumber());
						System.out.println("The new balance is now\t"+ account1.getBalance());
					}
				}
			}
		}
		else
			System.out.println("There are no accounts at this time.");
		DataManager.saveBankData(bank);
	}
	public void modAccountBalance(Customer customer, Scanner scan) {
		//view customer accounts
		//options to withdraw, transfer, deposit
		//Get amount from transaction
		//bank."selectedTransaction"(customer selected account, other account, amount)
		List<Account> accounts = customer.getAccounts();
		if(accounts.size()!=0) {
			System.out.println("\nWhat would you like to do?\n1:\tWithdraw\n2:\tDeposit\n"
					+ "3:\tTransfer\n4:\tExit\nEnter your choice:\t");
			boolean success = false;
			int choice =0;
			while(!success) {
				choice = this.getNumber(scan);
				if(choice ==-1) 
					return;
				else if(choice>0 || choice <5) {
					success=true;
				}else
					System.out.println("Incorrect choice detected!!\nTry again...");
			}
			if(choice ==4) {
				return;
			}else {
				for (int i=0; i< accounts.size(); i++) {
					System.out.println((i+1) +"\t"+accounts.get(i) + "\t" + accounts.get(i).getStatus());
				}
				System.out.println("Select an account\n");
				success = false;
				int choice2 =0;
				while(!success) {
					choice2 = this.getNumber(scan);
					if(choice ==-1) 
						return;
					else if(choice2>0 && choice2 <=accounts.size()) {
						success=true;
					}else
						System.out.println("Incorrect choice detected!!\nTry again...");
				}
				Account account1 = accounts.get(choice2-1);
				if (choice == 3){
					//Transefer between accounts
					if(accounts.size()>1) {
						System.out.println("Select the other account");
						success = false;
						int choice3 =0;
						while(!success) {
							choice3 = this.getNumber(scan);
							if(choice ==-1) 
								return;
							else if(choice3>0 && choice3 <=accounts.size() && choice3 != choice2) {
								success=true;
							}else
								System.out.println("Incorrect choice detected!!\nTry again...");
						}
						Account account2 = accounts.get(choice3-1);
						System.out.println("Enter an amount\n");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>0 && amount <account1.getBalance()) {
								success=true;
							}else
								System.out.println("ERROR:\tamount cannot be greater than balance!!\n"
										+ "ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						this.bank.transfer(account1, account2, amount);
						System.out.println("$"+amount+" has been transferred to the account: "+account2.getAccountNumber());
						System.out.println("The new balance is for " + account1.getAccountNumber() + " is now\t"+ account1.getBalance());
						System.out.println("The new balance is for " + account2.getAccountNumber() + " is now\t"+ account2.getBalance());
					}else
						System.out.println("There are no other accounts to transer to");
				}else {
					if (choice ==1) {
						//withdraw
						System.out.println("Enter an amount");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>0 && amount <account1.getBalance()) {
								success=true;
							}else
								System.out.println("ERROR:\tAmount cannot be greater than balance!!\n"
										+ "ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						bank.withdraw(account1, amount);
						System.out.println("$"+amount+" has been withdrawn from the account: "+account1.getAccountNumber());
						System.out.println("The new balance is for " + account1.getAccountNumber() + " is now\t"+ account1.getBalance());
					}else if (choice == 2) {
						//deposit
						System.out.println("Enter an amount");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>0) {
								success=true;
							}else
								System.out.println("ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						bank.deposit(account1, amount);
						System.out.println("$"+amount+" has been deposited to the account: "+account1.getAccountNumber());
						System.out.println("The new balance is now\t$"+ account1.getBalance());
						
					}
				}
			}
		}else {
			if(bank.getNumAccounts()!=0) {
				System.out.println("You can only make a deposit at this time. What would you like to do?\n"
						+ "1:\tDeposit\n2:Exit\n");
				boolean success =false;
				int choice = 0;
				while(!success) {
					choice = this.getNumber(scan);
					if(choice ==-1) 
						return;
					else if(choice<1 && choice>2) {
						System.out.println("Incorrect choice detected.!!\nTry again...");
					}else
						success=true;
				}
				if(choice==2)
					return;
				else {
					System.out.println("Enter an account number\nEnter -1 to exit");
					success =false;
					while(!success) {
						choice = this.getNumber(scan);
						if(choice==-1) {
							return;
						}else if(!bank.accountNumbersContains(choice)) {
							System.out.println("That account number does not exist");
						}else {
							success=true;
						}
					}
					Account account = bank.getAccountByAccountNumber(choice);
					System.out.println("Enter an amount");
					success = false;
					double amount =0;
					while(!success) {
						amount = this.getAmount(scan);
						if(amount ==-1) 
							return;
						else if(amount>0) {
							success=true;
						}else
							System.out.println("ERROR:\tAmount must be a positive number.!!\nTry again...");
					}
					bank.deposit(account, amount);
					System.out.println("$"+amount+" has been deposited to the account: "+account.getAccountNumber());
					System.out.println("The new balance is now\t$"+ account.getBalance());
				}
			}else {
				System.out.println("There are no accounts in the bank at this time.\n");
			}
		}
	}

	public void customerApply(Customer customer, Scanner scan) {
		// TODO Auto-generated method stub
//		Application app = new Application(primaryCustomerID, secondaryCustomerID);
//		DataManager.saveApplication(app);
//		bank.addNumApplications();
		boolean confirm = false;
		while (!confirm) {
			System.out.println("Are you opening a joint account?");
			String choice2 ="";
			System.out.print("yes or no:\t");
			choice2 = scan.nextLine().trim();
			if (choice2.equals("yes")) {
				confirm = true;
				boolean confirm2 = false;
				System.out.println("Do you have the secondary customer's bank ID?");
				while (!confirm2) {
					System.out.print("yes or no:\t");
					choice2 = scan.nextLine().trim().toLowerCase();
					int secondaryCustomerID =0;
					if (choice2.equals("yes")) {
						confirm2 = true;
						boolean confirm3 = false;
						while(!confirm3) {
							System.out.println("Enter the secondary customer's bank ID?\nEnter -1 to exit to main menu.");
							System.out.print("Secondary Customer's bank ID");
							boolean getSecondaryCustomerID = false;
							while(!getSecondaryCustomerID) {
								System.out.print("Secondary Customer's bank ID");
								secondaryCustomerID = this.getNumber(scan);
								if(secondaryCustomerID ==-1) 
									return;
								else if (secondaryCustomerID < 3000000 || secondaryCustomerID !=-1)
										getSecondaryCustomerID = true;
								else
									System.out.println("Incorrect choice detected!!\nTry again...");
								
							}	
							if(this.bank.customerIDsContains(secondaryCustomerID)) {
								Customer secondaryCustomer = bank.getCustomerById(secondaryCustomerID);
								this.bank.customerApply(customer, secondaryCustomer);
								System.out.println("Secondary Customer: "+ secondaryCustomer.toString() +"\n");
								System.out.println("Application completed. An employee will check on your application and"
										+ " get back to you shortly. Thank you\n");
								confirm3=true;
							}else if(secondaryCustomerID ==-1){
								this.home(customer, scan);
							}else {
								System.out.println("Incorrect choice detected!!\nTry again...");
							}
						}
					}else if (choice2.equals("no")) {
						confirm2 = true;
						Customer secondary = this.registerCustomer(scan);
						this.bank.customerApply(customer, secondary);
						System.out.println("Application completed. An employee will check on your application and"
								+ " get back to you shortly. Thank you\n");
					}else if (choice2.equals("exit")) {
						confirm=true;
						this.home(customer, scan);
					}else
						System.out.println("Incorrect choice detected!!\nTry again...");
				}
			}else if (choice2.equals("no")) {
				confirm=true;
				Customer secondaryCustomer = null;
				this.bank.customerApply(customer, secondaryCustomer);
				System.out.println("Application completed. An employee will check on your application and"
						+ " get back to you shortly. Thank you\n");
			}else 
				System.out.println("Incorrect choice detected!!\nTry again...");
		}
	}
//	public void customerCheckApplication(Customer customer, Scanner scan) {
//		//bank.getApplication
//	}
	public void viewCustomerInfo(Employee employee, Scanner scan) {
		List<Customer> customers = bank.getCustomers();
		if (customers.size()==0) {
			System.out.println("There are no customers at this time");
		}
		for (Customer customer: customers) {
			System.out.println(customer.toString());
		}
		
	}
	public void viewCustomerInfo(Customer customer, Scanner scan) {
		System.out.println(customer.toString());
		
	}
	public void cancelAccount(Admin admin, Scanner scan) {
		List<Account> accounts = bank.getAccounts();
		if(accounts.size()==0) {
			System.out.println("There are no accounts at this time");
			return;
		}
		for (int i=0; i< accounts.size(); i++) {
			System.out.println(i +"\t"+accounts.get(i) + "\t" + accounts.get(i).getStatus());
		}
		boolean success = false;
		int choice =0;
		while (!success) {
			System.out.println("Please select an Account\n");
			System.out.print("Enter:\t");
			choice =this.getNumber(scan);
			if(choice ==-1) 
				return;
			else if((choice <= 0) || choice > accounts.size()+1 ) {
				System.out.println("Incorrect choice detected!!\nTry again...");
			}else
				success=true;
		}
		Account account = accounts.get(choice);
		if(account.isOpen()) {
			account.cancel(admin);
			System.out.println();
		}else {
			System.out.println("ERROR: The account's previous status was \"" +account.getStatus()+"\"");
		}
		System.out.println("The account's new status is \"" +account.getStatus()+"\"");
	}
	private String getUsername(Scanner scan) {
		boolean success = false;
		String username="";
		System.out.println("Enter a username\n");
		while(!success) {
			System.out.print("Username:\t");
			username = scan.nextLine().trim().replaceAll("\\s", "").toLowerCase();
			if(bank.userIdsContainsKey(username)) 
				System.out.println("that username is taken. Try again...");
			else
				success= true;
		}
		return username;
	}
	private String getPassword(Scanner scan) {
		String password = "";
		boolean success=false;
		System.out.println("Enter a password");
		while(!success) {
			System.out.print("Password:\t");
			password =scan.nextLine();
			System.out.println("Confirm password");
			System.out.print("Confirm password:\t");
			String password2 =scan.nextLine();
			if (password.equals(password2))
				success=true;
			else
				System.out.println("Passwords do not match. Try again...");
			
		}
		return password;
	}
	private String getFullName(Scanner scan) {
		String firstName = "";
		String lastName = "";
		boolean success=false;
		while(!success) {
			System.out.println("Enter a your first name");
			System.out.print("First name:\t");
			firstName =scan.nextLine().trim();
			System.out.println("Enter a your last name");
			System.out.print("Last name:\t");
			lastName =scan.nextLine().trim();
			boolean confirm = false;
			System.out.println("Is correct: " + firstName + " " + lastName);
			while(!confirm) {
				System.out.print("yes or no:\t");
				String addressConfirm = scan.nextLine().trim().toLowerCase();
				if(addressConfirm.equals("yes")) {
					confirm=true;
					success=true;
				}
				else if(addressConfirm.equals("no")) {
					System.out.println("Try again!!");
					confirm=true;
				}
				else
					System.out.println("Enter \"yes\" or \"no\"!!");
			}
		}
		return firstName + " " + lastName;
	}
	private String getAddress(Scanner scan) {
		String address = "";
		boolean success=false;
		while(!success) {
			System.out.println("Enter a your address in this format\n"
					+ "101 3rd st, Apt 201, New York, NY 10001");
			System.out.print("Address:\t");
			address =scan.nextLine().trim();
			boolean confirm = false;
			System.out.print("Is this address correct: " + address + "\nEnter \"yes\" or \"no\"");
			while(!confirm) {
				System.out.print("yes or no:\t");
				String addressConfirm = scan.nextLine().trim().toLowerCase();
				if(addressConfirm.equals("yes")) {
					confirm=true;
					success=true;
				}
				else if(addressConfirm.equals("no")) {
					System.out.println("Try again!!");
					confirm=true;
				}
				else
					System.out.println("Enter \"yes\" or \"no\"!!");
			}
		}
		return address;
	}
	private String getPhone(Scanner scan) {
		String phone="";
		boolean success=false;
		while(!success) {
			System.out.println("Enter a your phone number in this format\n"
					+ "678-982-2343");
			System.out.print("Enter:\t");
			phone =scan.nextLine().trim();
			boolean confirm = false;
			System.out.print("Is this phone number correct:\t" + phone);
			while(!confirm) {
				System.out.print("yes or no:\t");
				String phoneConfirm = scan.nextLine().trim().toLowerCase();
				if(phoneConfirm.equals("yes")) {
					confirm=true;
					success=true;
				}
				else if(phoneConfirm.equals("no")) {
					System.out.println("Try again!!");
					confirm=true;
				}
				else
					System.out.println("Enter \"yes\" or \"no\"");
			}
		}
		return phone;
	}
	private int getNumber(Scanner scan) {
		int choice =0;
		boolean success=false;
		while(!success) {
			try {
				System.out.print("Enter:\t");
				choice = Integer.parseInt(scan.nextLine().trim());
				//choice = scan.nextInt();
				success =true;
			}catch(Exception e) {
				System.out.println("Enter numbers only!!");
			}
		}
		return choice;
	}
	private double getAmount(Scanner scan) {
		boolean success=false;
		double choice =0;
		while(!success) {
			try {
				System.out.print("Amount:\t");
				choice = scan.nextDouble();
				//choice = Double.parseDouble(scan.nextLine().trim());
				success =true;
			}catch(NumberFormatException e) {
				System.out.println("Enter numbers only!!");
			}
		}
		return choice;
	}
}
