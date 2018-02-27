package com.revature.banking.view;

import java.util.Scanner;

import com.revature.banking.fileio.view.UserViewFileIO;
import com.revature.banking.jdbc.view.UserView;

public class PersistenceView {

	public PersistenceView() {
		
	}
	public static void main(String[] args) {
		PersistenceView view=new PersistenceView();
		Scanner scan = new Scanner(System.in);
		view.selectPersistence(scan);
	}

	protected void selectPersistence(Scanner scan) {
		System.out.println("Welcome to the Core 6 Bank\n"
				+ "Select persistence type\n"
				+ "1:\tFile\n"
				+ "2:\tDatabase\n"
				+ "3:\tExit");
		boolean success=false;
		int choice = 0;
		while(!success) {
			choice = getNumber(scan);
			if (choice>0 && choice<=3) {
				success=true;
			}else
				System.out.println("Incorrect choice detected. Try again..");
		}
		if(choice==1)
			UserViewFileIO.launch(scan);
		else if(choice == 2)
			UserView.launch(scan);
		else {
			System.out.println("Closing application..");
			System.exit(0);
		}
		selectPersistence(scan);
	}
	protected int getNumber(Scanner scan) {
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
	protected double getAmount(Scanner scan) {
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
