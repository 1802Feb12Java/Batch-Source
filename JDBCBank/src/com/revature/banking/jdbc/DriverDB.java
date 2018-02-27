package com.revature.banking.jdbc;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class DriverDB {
	static Logger log = Logger.getLogger(DriverDB.class.getName());

	/**
	 * Main method runs everything. Holds logic for program
	 * 
	 * @param args
	 *            Arguments for running the program
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void main(String[] args) throws SQLException {
		Scanner input = new Scanner(System.in);
		boolean end = false;
		String user, pass;
		String version = "2.0.1";

		log.debug("STARTING PROGRAM VERSION [" + version + "]");

		System.out.println("Welcome to Bank App ver: " + version + "\n");

		while (!end) {

			System.out.println("\nWhat would you like to do?\n[1: Login, 2: Register, 3: Exit]\t");
			try {
				int branchOne = Integer.valueOf(input.nextLine());
				if (branchOne > 3 || branchOne < 1) {
					System.out.println("Please enter a correct value.");
				}
				// check input and continue
				else {
					switch (branchOne) {
					case 1:
						System.out.print("Login\n\tUsername:\t");
						user = input.nextLine();
						System.out.print("\tPassword:\t");
						pass = input.nextLine();

						if (pass.equals(UserDB.getPasswordToCheck(user))) {
							System.out.println("\nLogged in as " + UserDB.getFirstName(user) + ".");
						} else {
							System.out.println("Incorrect username or password");
							break;
						}

						// split to customer, employee, and admin
						if (UserDB.getAccountType(user) == 0) {
							// customer
							log.info("USER [" + user + "] LOGGED IN AS CUSTOMER");
							CustomerDB.customerMenu(user);
						} else if (UserDB.getAccountType(user) == 1) {
							// employee
							log.info("USER [" + user + "] LOGGED IN AS EMPLOYEE");
							EmployeeDB.employeeMenu(user);
						} else if (UserDB.getAccountType(user) == 2) {
							// administrator
							log.info("USER [" + user + "] LOGGED IN AS ADMINISTRATOR");
							AdminDB.adminMenu(user);
						}
						break;
					case 2:
						System.out.print("Register\nPick a Username [Enter \"c\" to cancel]: \t");
						user = input.nextLine();
						if (user.toUpperCase().equals("C")) {
							break;
						}
						System.out.print("Create a password:\t");
						pass = input.nextLine();

						CustomerDB.createCustomer(user, pass);
						break;
					case 3:
						System.out.println("Exiting...");
						input.close();
						UserDB.conn.close();
						log.debug("DATABASE CONNECTION CLOSED");
						log.debug("APPLICATION TERMINATED");
						System.exit(0);
					}
				}
			} catch (Exception e) {
				// don't do anything
			}
		}
	}
}