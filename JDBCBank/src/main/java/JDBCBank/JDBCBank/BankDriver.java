package JDBCBank.JDBCBank;

import java.util.Scanner;

import org.apache.log4j.Logger;

import JDBCBank.JDBCBank.consoleinterface.AdminGUI;
import JDBCBank.JDBCBank.consoleinterface.CustomerGUI;

public class BankDriver {
	   static Logger log = Logger.getLogger(BankDriver.class);
	public static void display() {
		Scanner sc = new Scanner(System.in);
		int input;
		AdminGUI adgui = new AdminGUI();
		CustomerGUI cusgui = new CustomerGUI();
		//logger.info("HI");
		System.out.println("================ Welcome to bank of Beannie===============");
		System.out.println("\t1.Customer Portal");
		System.out.println("\t2.Administrator Portal");
		System.out.println("\t3.Exit");

		input = sc.nextInt();
		
		switch(input) {
		case 1:
			cusgui.displayCustomerGUI();
		case 2:
			adgui.displayAdminMainView();
		}
	}

	public static void main(String[] args) {
		BankDriver.display();
	}

}
