package account.features;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import customer.information.CustomerInformation;

public abstract class CreateRemoveApproveDeny {
	public static void  createAccount()
	{
		Account newAccount = new Account();
		ArrayList<String> tokens = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new Account names separated by spaces");
		String stringifyUsernames = sc.nextLine();
		String [] parseTokens = stringifyUsernames.split(" ");
		for(String aToken : parseTokens)
		{
			tokens.add(aToken);
		}
		
		newAccount.setUserNames(tokens);
		System.out.println("Enter new Password");
		String password = sc.nextLine();
		newAccount.setPassword(password);
		CustomerInformation.customerRecords.add(newAccount);
	}
	public static void cancelDeleteAccount()
	{
		Account delete = new Account(CustomerInformation.searchCustomerInformation());
		CustomerInformation.customerRecords.remove(CustomerInformation.iterator);
		CustomerInformation.iterator = 0;
		System.out.println("Record Sucessfully Removed");;
	}
	public static void approveDenyAccount()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Type a to approve or Type d to deny Accounts");
		String type = sc.nextLine();
		if(type.compareTo("a") == 0)
		{
			System.out.println("Account Approved");
		}
		else
		{
			System.out.println("Account Denied");
		}
	}
}
