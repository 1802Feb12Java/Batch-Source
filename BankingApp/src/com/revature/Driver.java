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
		
		ArrayList<Customer> allCusts = new ArrayList<>();
		addToList(test, allCusts);
		addToList(test2,allCusts);
		
		//((List<Customer>) allCusts.get(0)).contains("sal");
		
		//System.out.println(allCusts.toString());
		
		//String custInfo = "CustomerAccounts.txt";
		//Customer currentCust = new Customer("","");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Goliath National Bank!\n");
		while(true) {
			System.out.println("Are you a <customer>, <employee>, or <admin>?");
			String user = sc.next();
			while(true) {
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
							currentCust.runCustomerApp();
							break;
							
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
								           currentCust.runCustomerApp();
								           break;
								       }else {
								    	   System.out.println("Credentials don't match. Try Again");
											continue;
								       }
								}
								
								
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
							return;
						}
						else {
							System.out.println("Invalid input. Try again.");
							option = sc.next();
						}
						
					}
					//break;
					
				}else if(user.equals("admin")) {
					
				}
				
				else {
					System.out.println("Invalid input. Try Again.");
					user= sc.next();
				}
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
	
}
