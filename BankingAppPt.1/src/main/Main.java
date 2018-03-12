package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import account.features.Account;
import account.features.CreateRemoveApproveDeny;
import customer.information.CustomerInformation;

public class Main {

	public static void main(String[] args) {
		CustomerInformation bankView = new CustomerInformation();
		ArrayList <Account> serialObject = new ArrayList<>();
		
		boolean willNotExit = true;
		try {
			
			
			
			
			
		    // read object from file
			FileInputStream fis = new FileInputStream("bankRecords.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			serialObject = (ArrayList<Account>) ois.readObject();
			CustomerInformation.customerRecords = serialObject;
			
			ois.close();

			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		do {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Bank level access- 0 for BankAdmin or 1 for Employees");
			String access = sc.nextLine();
			
				
				if(access.equals("0")) 
				{
					
					System.out.println("Create new Account y/n");
					String newAccnt = sc.nextLine();
					
					if(newAccnt.equals("y"))
					CreateRemoveApproveDeny.createAccount();
					System.out.println("Delete Account y/n");
					String deleteAccnt = sc.nextLine();
					if(deleteAccnt.equals("y"))
					CreateRemoveApproveDeny.cancelDeleteAccount();
					bankView.bankAdminView();
					
					
				}
				else
				{
					System.out.println("View a record y/n");
					String view = sc.nextLine();
					if(view.equals("y"))
					{
						Account anAccount = new Account();
						anAccount = CustomerInformation.searchCustomerInformation();
						System.out.println("Usernames = " + anAccount.getUserNames().toString() + " Password = " + anAccount.getPassword() + " Balance = " + anAccount.getBalance());
					}
						
				}
				System.out.println("Do you want to Exit program? y/n");
				String exit = sc.nextLine();
				if(exit.equals("y")) {
					 willNotExit = false;
				}
		}while(willNotExit);
		
		try {
			
			
			
			
			
			// write object to file
			serialObject = CustomerInformation.customerRecords;
			FileOutputStream fos = new FileOutputStream("bankRecords.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(serialObject);
			oos.close();

			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
