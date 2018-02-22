package com.revature;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import com.revature.beans.*;

public class DriverTake2 {
	
	public static void main(String[] args) {
		Customer test = new Customer("sal", "allen"); //hard coded a few existing account
		Customer test2= new Customer("julian", "chris");
		Customer test3= new Customer("sonam", "john");
		
		ArrayList<Customer> allCusts = new ArrayList<>();
		addToList(test, allCusts);
		addToList(test2,allCusts);
		addToList(test3,allCusts);
		
		String outfile = "CustomerAccounts.txt";
		overwriteFile(allCusts , outfile);
			
		Scanner sc = new Scanner(System.in);
		while(true) {
			mainInterface(allCusts); //this runs the app
		}					//unfortunately it never stops
	}
	
	public static void addToList(Customer c, ArrayList<Customer> a) {
		a.add(c);
	}
	
	public static void mainInterface(ArrayList<Customer> a) { //this is the main menu; starting point
		String outfile = "CustomerAccounts.txt";
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Goliath National Bank!!\n");
		boolean running = true;
		while(running) {
			while(true) {
				System.out.println("Are you a <customer>, <employee>, or <admin>?");
				String user = sc.next();
				if(user.equals("customer")) {
					Customer currentCust = new Customer("","");
					System.out.println("Create <new> account, or <login> to an existing one?");
					String customer = sc.next();
					boolean entry = true;
					while(entry) {
						if(customer.equals("new")) {
							System.out.print("Create username: ");
							String un = sc.next();
							System.out.print("Set a password: ");
							String pw = sc.next();
							Customer newCust = new Customer(un, pw);
							addToList(newCust, a);
							currentCust = newCust;
							if(currentCust.isApproved() == true) {
								runCustomerApp(currentCust, a);
							}else {
								System.out.println("An employee must first approve your account."
										+ "\n=============================================================");
							}
							
							overwriteFile(a, outfile);
							entry = false;
						}else if(customer.equals("login")) {
							while(true) {
								System.out.print("Enter username: ");
								String un = sc.next();
								System.out.print("Enter password: ");
								String pw = sc.next();
								boolean check = false;	
								for(int i =0; i<a.size(); i++){
								       Customer login = a.get(i);
								       if(login.getUsername().equals(un) && login.getPassword().equals(pw)) {
								           currentCust = login;
								           check = true;
								       }      
								}
								if(check == true) {
									if(currentCust.isApproved() == true) {
										runCustomerApp(currentCust, a);
									}else {
										System.out.println("An employee must first approve your account."
												+ "\n=============================================================");
									}
									break;
								}else {
									System.out.println("Credential don't match. Try again");
								}		
							}
							
							entry = false;
						}else {
							System.out.println("Invalid input. Try Again.");
							customer= sc.next();
						}
					}

					break;
				}else if(user.equals("employee")) {
					Employee barney = new Employee(80085);
					System.out.print("Enter Employee ID Number: ");
					int id_num = sc.nextInt();
					while(id_num != barney.getEmployeeID()) {
						System.out.print("Incorrect. Please enter the correct number : ");
						id_num = sc.nextInt();
					}
					runEmployeeApp(barney, a);
					
					break;
				}else if(user.equals("admin")) {
					Administrator marshall = new Administrator("GNB");
					System.out.print("Enter the master code: ");
					String code = sc.next();
					while(!code.equals(marshall.getMaster())) {
						System.out.print("Thats not the master code!! Enter the real code: ");
						code = sc.next();
					}
					runAdminApp(marshall, a);
						
					break;
				}else {
					System.out.println("Invalid input. Try Again.");
					user= sc.next();
				}
			}

			running = false;
		}
	}

	private static void runCustomerApp(Customer c, ArrayList<Customer> a) { //interface for customers only
		String outfile = "CustomerAccounts.txt";
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("\nSelect an option:\n"
					+ "\tWithdraw money from your account ----<w>\n"
					+ "\tDeposit money into your account -----<d>\n"
					+ "\tTransfer money to anothers account --<t>\n"
					+ "\tLogout ----------------------------- <lo>");
			
			String action = sc.next();
			if(action.equals("w")) {
				System.out.print("How much do you want to take out?   $");
				double withdraw = sc.nextDouble();
				while(true) {
					if(withdraw>c.getAccBalance()) {
						System.out.print("Invalid amount. Please enter value < your account balance \n$");
						withdraw = sc.nextDouble();
					}else if(withdraw<0) {
						System.out.print("Invalid amount. Please enter value >= 0 \n$");
						withdraw = sc.nextDouble();
					} else {
						break;
					}
				}
				c.withdrawMoney(withdraw);
				overwriteFile(a, outfile);
				continue;
				
			} else if(action.equals("d")) {
				System.out.print("How much do you want to deposit?   $");
				double deposit = sc.nextDouble();
				while(deposit < 0) {
					System.out.print("Invalid amount. Please enter value >= 0 \n$");
					deposit = sc.nextDouble();
				}
				c.depositMoney(deposit);
				overwriteFile(a, outfile);
				continue;
				
			} else if(action.equals("t")) {
				Customer lucky = new Customer("","");
				System.out.print("Enter the username of the account you want to send money to: ");
				while(true) {
					String person = sc.next();
					boolean checking = false;
					
					for(int i = 0; i<a.size(); i++) {
						Customer temp = a.get(i);
						if(person.equals(temp.getUsername())) {
							checking = true;
							lucky = temp;
						}
					}
					if(checking == true) {
						System.out.print("How much do you want to send?  $");
						double decision = sc.nextDouble();
						while(decision<0 | decision>c.getAccBalance()) { 
							System.out.print("Invalid amount. Try again.\n$");
							decision = sc.nextDouble();
						}
						c.transferMoney(lucky, decision);
						overwriteFile(a, outfile);
						
						break;
					}else {
						System.out.print("User does not exist. Please enter existing user: ");
					}
				}
				

				continue;
				
			} else if(action.equals("lo")) {
				System.out.println("\n=============================================================");
				break;
			} else {
				System.out.println("Invalid input. Try Again.");
			}
		}
		
	}
	
	private static void runEmployeeApp(Employee e, ArrayList<Customer> a) { //interface for employees
		String outfile = "CustomerAccounts.txt";
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Select an option:\n"
					+ "\tView all customers info --------------<v>\n"
					+ "\tSelect customer to approve or deny ---<s>\n"
					+ "\tLogout to Main Menu -----------------<lo>");
			String action = sc.next();
			if(action.equals("v")) {
				e.viewCustInfo(a);
				System.out.println("\n");
				continue;
			}else if(action.equals("s")) {
				Customer selectedCust = new Customer("","");
				System.out.print("Enter customer username: ");
				while(true) {
					String user = sc.next();
					boolean checking = false;
					
					for(int i = 0; i<a.size(); i++) {
						Customer temp = a.get(i);
						if(user.equals(temp.getUsername())) {
							checking = true;
							selectedCust = temp;
						}
					}
					if(checking == true) {
						System.out.println("Do you want to approve<a> or deny<d> the account?");
						String decision = sc.next();
						while(!decision.equals("a") & !decision.equals("d")) {
							System.out.println("Invalid input. Try again.");
							decision = sc.next();
						}
						if(decision.equals("a")) {
							selectedCust.setApproved(true);
							overwriteFile(a, outfile);
							System.out.println("Customer approved!");
						}else if(decision.equals("d")) {
							selectedCust.setApproved(false);
							overwriteFile(a, outfile);
							System.out.println("Customer denied!");
						}
						
						break;
					}else {
						System.out.print("User does not exist. Please enter existing user: ");
					}
				}
				
				continue;
			}else if(action.equals("lo")) {
				System.out.println("\n=============================================================");
				break;
			}else {
				System.out.println("Invalid input. Try Again.");
			}
			
		}
	}
	
	private static void runAdminApp(Administrator admin, ArrayList<Customer> a) { //interface for admins
		String outfile = "CustomerAccounts.txt";
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Select an option:\n"
					+ "\tView all customers info --------------<v>\n"
					+ "\tSelect customer account to modify ----<s>\n"
					+ "\tLogout to Main Menu -----------------<lo>");
			String action = sc.next();
			if(action.equals("v")) {
				admin.viewCustInfo(a);
				System.out.println("\n");
				continue;
			}else if(action.equals("s")) {
				Customer selected = new Customer("","");
				System.out.print("Enter customer username: ");
				while(true) {
					String user = sc.next();
					boolean checking = false;
					
					for(int i = 0; i<a.size(); i++) {
						Customer temp = a.get(i);
						if(user.equals(temp.getUsername())) {
							checking = true;
							selected = temp;
						}
					}
					if(checking == true) {
						System.out.println("Select an option:\n"
								+ "\tApprove the account -----------<a>\n"
								+ "\tDeny the account --------------<d>\n"
								+ "\tWithdraw money from account ---<w>\n"
								+ "\tDeposit money into account ----<g>\n"
								+ "\tTranser money to another acc --<t>\n"
								+ "\tCancel the account ------------<c>\n"
								+ "\tBack out   --------------------<b>");
						String decision = sc.next();
						boolean check = true;
						while(check) {
							if(decision.equals("a")) {
								selected.setApproved(true);
								overwriteFile(a, outfile);
								System.out.println("Customer approved!");
								check = false;
							}else if(decision.equals("d")) {
								selected.setApproved(false);
								overwriteFile(a, outfile);
								System.out.println("Customer denied and removed!");
								check = false;
							}else if(decision.equals("w")) {
								System.out.print("How much do you want to take out?   $");
								double withdraw = sc.nextDouble();
								while(true) {
									if(withdraw>selected.getAccBalance()) {
										System.out.print("Invalid amount. Please enter value < your account balance \n$");
										withdraw = sc.nextDouble();
									}else if(withdraw<0) {
										System.out.print("Invalid amount. Please enter value >= 0 \n$");
										withdraw = sc.nextDouble();
									} else {
										break;
									}
								}
								selected.withdrawMoney(withdraw);
								overwriteFile(a, outfile);

								check = false;
							}else if(decision.equals("g")) {
								System.out.print("How much do you want to deposit?   $");
								double deposit = sc.nextDouble();
								while(deposit < 0) {
									System.out.print("Invalid amount. Please enter value >= 0 \n$");
									deposit = sc.nextDouble();
								}
								selected.depositMoney(deposit);
								overwriteFile(a, outfile);

								check = false;
							}else if(decision.equals("t")) {
								Customer lucky = new Customer("","");
								System.out.print("Enter the username of the account you want to send money to: ");
								while(true) {
									String person = sc.next();
									boolean checker = false;
									
									for(int i = 0; i<a.size(); i++) {
										Customer temp = a.get(i);
										if(person.equals(temp.getUsername())) {
											checker = true;
											lucky = temp;
										}
									}
									if(checker == true) {
										System.out.print("How much do you want to send?  $");
										double money = sc.nextDouble();
										while(money<0 | money>selected.getAccBalance()) {
											System.out.print("Invalid amount. Try again.\n$");
											money = sc.nextDouble();
										}
										selected.transferMoney(lucky, money);
										overwriteFile(a, outfile);
										
										break;
									}else {
										System.out.print("User does not exist. Please enter existing user: ");
									}
								}
								
								check = false;
							}else if(decision.equals("c")) {
								a.remove(selected);
								overwriteFile(a, outfile);
								System.out.println("Customer removed from the system.");
								
								check = false;
							}else if(decision.equals("b")) {
								check = false;
							}else {
								System.out.println("Invalid input. Try again.");
								decision = sc.next();
							}
						}
						
						break;
					}else {
						System.out.print("User does not exist. Please enter existing user: ");
					}
				}
				continue;
			}else if(action.equals("lo")) {
				System.out.println("\n=============================================================");
				break;
			}else {
				System.out.println("Invalid input. Try Again.");
			}
		}	
	}
	
	public static void overwriteFile(Serializable s, String file) { //sends data to file to write but never reads
		try(FileOutputStream fileOut= new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut))
		{
			out.reset();//empties file and rewrites all customers in the array
			out.writeObject(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
