package com.revature.ui.basic;

import java.util.Scanner;

public class AdminScreen extends EmployeeScreen {
	@Override
	public void execute() {
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		String cmd;
		String[] cmdSplit;
		
		do {
			System.out.println(">>> ");
			cmd = userIn.nextLine();
			cmdSplit = cmd.split("\\s+");
			
			if(cmdSplit.length == 0) {
				continue;
			}
			if(cmdSplit[0].equals("deposit")) {
				deposit(cmdSplit);
			} else if(cmdSplit[0].equals("withdraw")) {
				withdraw(cmdSplit);
			} else if(cmdSplit[0].equals("transfer")) {
				transfer(cmdSplit);
			} else if(cmdSplit[0].equals("lsacct")) {
				lsacct(cmdSplit);
			} else if(cmdSplit[0].equals("logout")) {
				logout(cmdSplit);
				break;
			} else if(cmdSplit[0].equals("exit")) {
				exit(cmdSplit);
				break;
			} else if(cmdSplit[0].equals("cancel")) {
				
			} else if(cmdSplit[0].equals("approve")) {
				
			} else if(cmdSplit[0].equals("deny")) {
				
			} else {
				System.out.println("Unknown command.");
				continue;
			}
		} while(true);
		
		userIn.close();
	}
}
