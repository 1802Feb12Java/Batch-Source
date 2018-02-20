/*  Driver for the Banking App
 * 
 *  @author Dominic Nguyen
 */

package com.revature.project1;

//import java.util.ArrayList;

//import com.revature.io.IO;

public class Driver {

	public static void main(String[] args) {
		int selection = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		int m = 1;
		String[] accountInfo = new String[100];
		String[] jointAccountInfo = new String[100];
		double amountWithdrawn = 0;
		double amountDeposited = 0;
		double amountTransfered = 0;
		double totalAmount[] = new double[100];
		BankingApp b = new BankingApp();
		//ArrayList<String> array = new ArrayList<>();
		//ArrayList<Double> balance = new ArrayList<>();
		IO io = new IO();
		int loop = 0;
		
		//System.out.println(io.readInputStreamContents());
		
		while(loop == 0) {
			// make a selection to register or open an account
			selection = b.bankingMenu();
		
			if(selection == 1) {
				accountInfo = b.register(accountInfo, i);
			}
			else if(selection == 2) {
				accountInfo = b.openAccount(accountInfo, i);
				totalAmount[j] = 0.00;
				//balance.add(0.00);
			}
		
			System.out.println("Account information: " + accountInfo[i]);
			System.out.println("Balance: " + totalAmount[j]);
			//System.out.println("Account information: " + array);
			//System.out.println("Balance: " + balance);
		
			// joint account
			accountInfo = b.jointAccount(accountInfo, i);
			totalAmount[j] = 0.00;
			//balance.add(0.00);
		
			System.out.println("Account information: " + accountInfo[i]);
			System.out.println("Balance: " + totalAmount[j]);
		
			/*
			for(k = 0; k < 3*m; k++) {
				io.writeOutputStreamContents(accountInfo, k);
			}
			*/
		
			//System.out.println(array.get(0));
		
			/*
			int i = 0;
			for(String str : array) {
				if(str.equals("customer")) {
					System.out.println(array.get(i-2));
				}
				i++;
			}*/
		
			// withdraw, deposit, or transfer funds
			while(selection != 4) {
				selection = b.bankOptions();
		
				if(selection == 1) {
					totalAmount[j] = b.withdraw(totalAmount[j]);
				}
				else if(selection == 2) {
					totalAmount[j] = b.deposit(totalAmount[j]);
				}
				else if(selection == 3) {
					totalAmount[j] = b.transfer(totalAmount[j]);
				}
			}
			
			System.out.println("Balance: " + totalAmount[j]);
			
			i = i + 3;
			j++;
			m++;
		}  // outer while loop
	}  // main method
}
