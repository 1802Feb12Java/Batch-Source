package com.revature;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.*;

public class Driver {
	public static void main(String[] args) {
		Employee worker = new Employee(9001);
		
		Customer test = new Customer("sal", "allen");
		Customer test2= new Customer("julian", "chris");
		Customer test3= new Customer("sonam", "john");
		
		ArrayList<Customer> allCusts = new ArrayList<>();
		addToList(test, allCusts);
		addToList(test2,allCusts);
		addToList(test3,allCusts);
		
		//((List<Customer>) allCusts.get(0)).contains("sal");
		
		//System.out.println(allCusts.toString());
		
		//String custInfo = "CustomerAccounts.txt";
		//Customer currentCust = new Customer("","");
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Goliath National Bank!\n");
		while(run) {
			System.out.println("Are you a <customer>, <employee>, or <admin>?");
			String user = sc.next();
			
			boolean enter1 = true;
			while(enter1) {
				if(user.equals("customer")) {
					Customer currentCust = new Customer("","");
					System.out.println("Create <new> account, or <login> to an existing one?");
					String customer = sc.next();
					while(true) {
						if(customer.equals("new")) {
							System.out.print("Create username: ");
							String un = sc.next();
							System.out.print("Set a password: ");
							String pw = sc.next();
							Customer newCust = new Customer(un, pw);
							addToList(newCust, allCusts);
							//writeAccToFile(newCust, custInfo);
							currentCust = newCust;
							runCustomerApp(currentCust);
							//user = sc.next();
							enter1 = false;
							
							//break;
							
						}else if(customer.equals("login")) {
							while(true) {
								System.out.print("Enter username: ");
								String un = sc.next();
								System.out.print("Enter password: ");
								String pw = sc.next();
								
								
								for(int i =0; i<allCusts.size(); i++){
								       Customer login = allCusts.get(i);
								       if(login.getUsername().equals(un) && login.getPassword().equals(pw)) {
								           currentCust = login;
								           runCustomerApp(currentCust);
								           //run = false;
								           System.out.println("TESTING");
								       } 
								       // System.out.println("Credentials don't match. Try Again");
										//	continue;
								       
								}
								
								System.out.println("Credentials don't match. Try Again");
								
								
								
								
								break;
							
							} 
							}else {
								System.out.println("Invalid input. Try Again.");
								customer= sc.next();
							}
						break;
					}
					break;
				}else if(user.equals("employee")) {
					//Employee slave = new Employee(0);
					System.out.print("Enter Employee ID: ");
					int id_num = sc.nextInt();
					while(id_num != worker.getEmployeeID()) {
						System.out.println("Wrong ID Number. Enter the correct Employee ID Number");
						id_num = sc.nextInt();
					}
					//worker.runEmployeeApp();
					while(true) {
						System.out.println("\nSelect an option: View customer accounts --<view>\n"
										+  "                  Select an account    ----<select>\n"
										+  "                  Logout           --------<logout>");
						String option = sc.next();
						if(option.equals("view")) {
							worker.viewCustInfo(allCusts);
						} else if(option.equals("select")) {
							System.out.println("Which username do you select? ");
							String select = sc.next();
							for(int i = 0; i < allCusts.size(); i++) {
								Customer temp = allCusts.get(i);
								if(select.equals(temp.getUsername())) {
									System.out.println("<approve> or <deny> application?");
									String a_d = sc.next();
									if(a_d.equals("approve")) {
										
									}
								} else {
									System.out.println("Invalid username. Try again");
									option = sc.next();
								}
							}
						}else if(option.equals("logout")) {
							enter1 = false;
						}
						else {
							System.out.println("Invalid input. Try again.");
							option = sc.next();
						}
						
					}
					//break;
					
				}else if(user.equals("admin")) {
					
				}else {
					System.out.println("Invalid input. Try Again.");
					user= sc.next();
				}
				enter1 = false;
			}
			break;
		}
		
		
		//System.out.println(allCusts.toString());
		
		sc.close();
	}
	
	public static void addToList(Customer c, ArrayList<Customer> a) {
		a.add(c);
		//System.out.println("Account created!");
	}
	
	public static void writeAccToFile(Serializable s, String file) {
		try(FileOutputStream fileOut= new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut))
		{
			out.writeObject(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean login(ArrayList<Customer> al, String user, String pass) {
		for(int i =0; i<al.size(); i++){
		       Customer t = al.get(i);
		       if(t.getUsername().equals(user) && t.getPassword().equals(pass)) {
		           return true;
		       }else {
		    	   return false;
		       }
		
		}
		return false;
	}
	
	public static void runCustomerApp(Customer c) {
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		//if approved == true then run code below
		while(true) {
			System.out.println("\nEnter <withdraw>, <deposit>, or <transfer> to make changes to your account"
					+ "\n Or type <logout> to exit");
			
			String action = sc.next();
			if(action.equals("withdraw")) {
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
				continue;
				
			} else if(action.equals("deposit")) {
				System.out.print("How much do you want to deposit?   $");
				double deposit = sc.nextDouble();
				while(deposit < 0) {
					System.out.print("Invalid amount. Please enter value >= 0 \n$");
					deposit = sc.nextDouble();
				}
				c.depositMoney(deposit);
				continue;
				
			} else if(action.equals("transfer")) {
				//transfer money to different account
				continue;
				
			} else if(action.equals("logout")) {
				break;
			} else {
				System.out.println("Invalid input. Try Again.");
				action = sc.next();
			}
			//return;
		}
		sc.close();
	}
	
}
