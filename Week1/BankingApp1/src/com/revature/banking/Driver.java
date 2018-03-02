package com.revature.banking;

import java.util.Scanner;

public class Driver implements Storage {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		boolean end = false;

		System.out.println("Welcome to Bank App ver: 0.0.1\n");

		while (!end) {

			System.out.println("\nWhat would you like to do?\n[1: Login, 2: Register, 3: Exit]\t");
			try {
				int branchOne = Integer.valueOf(input.nextLine());
				if (branchOne > 3 || branchOne < 1) {
					System.out.println("Please enter a correct value.");
				}
				// check input and continue
				else {
					String user, pass;
					switch (branchOne) {
					case 1:
						System.out.print("Login\n\tUsername:\t");
						user = input.nextLine();
						System.out.print("\tPassword:\t");
						pass = input.nextLine();

						// check for user
						User checkAgainstThis = new User(user, pass);
						checkAgainstThis = checkAgainstThis.getUser(pass);

						// split to customer, employee, and admin
						if (checkAgainstThis.getAccountType() == 0) {

							boolean b2a = true;
							while (b2a) {
								System.out.println(
										"\nWhat would you like to do?\n[0: Balance, 1: Withdraw, 2: Deposit, 3: Transfer, 4: Log out]");
								int branchTwoA = Integer.valueOf(input.nextLine());

								switch (branchTwoA) {
								case 0:
									System.out.println("Balance: " + checkAgainstThis.getBalance());
									break;
								case 1:
									System.out.print("Enter withdrawal amount:\t");
									float amount = Float.valueOf(input.nextLine());
									((Customer) checkAgainstThis).withdraw(amount);
									break;
								case 2:
									System.out.print("Enter deposit amount:\t");
									amount = Float.valueOf(input.nextLine());
									((Customer) checkAgainstThis).deposit(amount);
									break;
								case 3:
									System.out.print("Enter transfer amount:\t");
									amount = Float.valueOf(input.nextLine());
									System.out.print("Enter transfer recipient:\t");
									String recipient = input.nextLine();
									((Customer) checkAgainstThis).transfer(recipient, amount);
									break;
								case 4:
									System.out.println("Logging out...");
									b2a = false;
									break;
								default:
									System.out.println("Please enter valid input.");
								}
							}
						} else if (checkAgainstThis.getAccountType() == 1) {
							System.out.println("\nEmployee");
							boolean stillInLoop = true;
							while (stillInLoop) {
								System.out.println(
										"\nWhat would you like to do?\n[1: View User, 2: Approve/Deny Applications, 3: Log Out]");
								int option = Integer.valueOf(input.nextLine());
								switch (option) {
								case 1:
									System.out.print("Enter account username:\t");
									String viewed = input.nextLine();
									Employee eUser = new Employee();
									eUser.viewUser(viewed);
									break;
								case 2:
									System.out.println("Coming soon™");
									break;
								case 3:
									System.out.println("Log Out");
									stillInLoop = false;
									break;
								default:
									System.out.println("Please enter correct input.");
									break;
								}
							}

						} else if (checkAgainstThis.getAccountType() == 2) {
							System.out.println("Admin");
							boolean stillInLoop = true;
							while (stillInLoop) {
								System.out.println(
										"\nWhat would you like to do?\n[1: View User, 2: Cancel Account, 3: Log Out]");
								int option = Integer.valueOf(input.nextLine());
								switch (option) {
								case 1:
									System.out.print("Enter account username:\t");
									String viewed = input.nextLine();
									Employee eUser = new Employee();
									eUser.viewUser(viewed);
									break;
								case 2:
									System.out.println("Enter username of account to be canceled:\t");
									String banHammered = input.nextLine();
									Admin aUser = new Admin();
									aUser.cancelAccount(banHammered);
									break;
								case 3:
									System.out.println("Log Out");
									stillInLoop = false;
									break;
								default:
									System.out.println("Please enter correct input.");
									break;
								}
							}
						} else if (checkAgainstThis.getAccountType() == -1) {
							boolean anotherBranch = true;
							while (anotherBranch) {
								System.out.println("\nWould you like to apply for an account? (Y/N)");
								String yesOrNo = input.nextLine();
								yesOrNo = yesOrNo.substring(0, 1).toUpperCase();
								switch (yesOrNo) {
								case "Y":
									anotherBranch = false;
									AccountApplication app = new AccountApplication();
									String[] processedApplication = app.applicationProcess();
									AccountApplication storedApp = new AccountApplication(
											checkAgainstThis.getUsername(), checkAgainstThis.getPassword(),
											processedApplication);
									storedApp.storeApplication();
									break;
								case "N":
									anotherBranch = false;
									System.out.println("Logging out.");
									break;
								default:
									System.out.println("Please enter correct input.");
								}
							}
						}
						break;
					case 2:
						System.out.print("Register\nPick a Username: \t");
						user = input.nextLine();
						System.out.print("Create a password:\t");
						pass = input.nextLine();

						Customer c1 = new Customer(user, pass);
						c1.createCustomer();
						break;
					case 3:
						System.out.println("Exiting...");
						input.close();
						System.exit(0);
					}
				}
			} catch (Exception e) {
				// don't do anything
			}
		}
	}
}