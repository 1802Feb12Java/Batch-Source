package customer.information;

import java.util.ArrayList;
import java.util.Scanner;

import account.features.Account;

public class CustomerInformation {
	
	public static ArrayList<Account> customerRecords = new ArrayList<>();
	public static int iterator = 0;
	public CustomerInformation() {}
	
	public CustomerInformation(Account newAccount)
	{
		customerRecords.add(newAccount);
	}
	public static Account searchCustomerInformation()
	{
		System.out.println("Search Customer by Username");
		Scanner sc = new Scanner(System.in);
		String userName = sc.nextLine();
		for(Account capture: customerRecords)
		{
			
			for(String aString: capture.getUserNames())
			{
				boolean nameExists = true;
				if(aString.equals(userName))
				{
					return capture;
				}
				else
				{
					nameExists = false;
					continue;
				}
			
			}
				
				
			iterator++;
			
		}
		
		return null;
	}
	public void bankAdminView()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("View customerRecords y/n");
		String view = sc.nextLine();
		if(view.equals("y"))
		{
			for(Account anAccount : customerRecords)
			{
				for(int i = 0; i < anAccount.getUserNames().size();i++)
				{
					System.out.println("Usernames = " + anAccount.getUserNames().get(i));
				}
				System.out.println( "Password = " + anAccount.getPassword());
				System.out.println("Balance = " + anAccount.getBalance());
				
			}
			iterator = 0;
		}
		System.out.println("Edit an Account y/n");
		String accountEdit = sc.nextLine();
		if(accountEdit.equals("y"))
		{
			
			System.out.println("Account Transfer y/n");
			String accountTransfer = sc.nextLine();
			if(accountTransfer.equals("y"))
			{
				System.out.println("Enter sender's name");
				Account senderAccount = new Account(searchCustomerInformation());
				customerRecords.set(iterator, senderAccount);
				iterator = 0;
				System.out.println("Enter reciever's name");
				Account reciever = new Account(searchCustomerInformation());
				customerRecords.set(iterator, reciever);
				iterator = 0;
				reciever.transfer(senderAccount);
				
			}
			
			System.out.println("Account deposit y/n");
			String deposit = sc.nextLine();
			
			if(deposit.equals("y"))
			{
				System.out.println("Enter Deposit Amount");
				int depositAmt = sc.nextInt();
				sc.nextLine();
				Account forDeposit = new Account(searchCustomerInformation());
				Account toDeposit = new Account();
				toDeposit = forDeposit.deposit(depositAmt);
				customerRecords.set(iterator, toDeposit);
				iterator = 0;
				
			}
			System.out.println("Account Withdraw y/n");
			String withdraw = sc.nextLine();
			if(withdraw.equals("y"))
			{
				System.out.println("Enter Withdraw Amount");
				int withdrawAmt = sc.nextInt();
				
				Account forWithdraw = new Account(searchCustomerInformation());
				Account toWithdraw = new Account();
				toWithdraw = forWithdraw.withdraw(withdrawAmt);
				customerRecords.set(iterator, toWithdraw);
				iterator = 0;
			}
			
		
		}
		
	}
}
