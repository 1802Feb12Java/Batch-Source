package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.beans.Superuser;
import com.revature.beans.User;

public class Driver {
	public static void main(String[] args) throws Exception {
		/*Connection connection = null;
		try {
			connection = DAOUtilities.getConnection();
			System.out.println("connection is good!\n");
		} catch (SQLException e) {
			System.err.println("it did not connect :(");
			e.printStackTrace();
		}*/
		
		
		
		//======================the real main begins===============================================
		
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while(run) {
			mainInterface();
			System.out.println("Do you want to exit the app? <yes> or <no>");
			String answer = sc.next();
			if(answer.equals("yes")) {
				System.out.println("Thank you for choosing Goliath National Bank!");
				sc.close();
				run = false;
			}else {
				System.out.println("I'll take that as a no"
						+ "\n=============================================================");
				continue;
			}
		}
	}
	
	
	
	private static void mainInterface() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Goliath National Bank!!\n");
		boolean running = true;
		while(running) {
			while(true) {
				System.out.println("Are you a <user> or an <admin>?");
				String user = sc.next();
				if(user.equals("user")) {
					UserDAO userDao = DAOUtilities.getUserDAO();
					System.out.println("Create <new> account, or <login> to an existing one?");
					//String customer = sc.next();
					boolean entry = true;
					while(entry) {
						String customer = sc.next();
						if(customer.equals("new")) {
							System.out.print("Create username: ");
							String un = sc.next();
							System.out.print("Set a password: ");
							String pw = sc.next();
							try {
								userDao.createAcc(un, pw, 0);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							User currentUser = getUser(un,pw);
							
							
							runUserApp(currentUser);
							
							
							entry = false;
						}else if(customer.equals("login")) {
							while(true) {
								System.out.print("Enter username: ");
								String un = sc.next();
								System.out.print("Enter password: ");
								String pw = sc.next();
								
								Connection connection = null;
								PreparedStatement ps = null;
								ResultSet rs = null;
								User current = new User(0,0,"","",0);
								try {
									connection = DAOUtilities.getConnection();
									String sql = "SELECT USERNAME,PASSWORD FROM USERACCOUNTS";
									ps = connection.prepareStatement(sql);
									
									rs = ps.executeQuery();
									boolean check = false;
									while(rs.next()) {
										String username = rs.getString("USERNAME");
										String password = rs.getString("PASSWORD");
										if(username.equals(un) && password.equals(pw)) {
											check = true;
											break;
										}
									}
									if(check==true) {
										current = getUser(un,pw);
										runUserApp(current);
									}else {
										System.out.println("Credentials didn't match. Try again");
										continue;
									}
												
								}catch(SQLException e) {
									e.printStackTrace();
								}
								
								break;
										
							}
							
							entry = false;
						}else {
							System.out.println("Invalid input. Enter <new> or <login>.");
						}
					}

					break;
				}else if(user.equals("admin")) {
					Superuser bs = new Superuser("barneyisawesome", "robin"); //hardcoded 2 admins
					Superuser me = new Superuser("marshmellow" , "lilypad");
					ArrayList<Superuser> list = new ArrayList<Superuser>(); //put them in arraylist to be found later
					list.add(bs);
					list.add(me);
					
					Superuser admin = new Superuser("","");
					while(true) {
						System.out.print("Enter username: ");
						String un = sc.next();
						System.out.print("Enter password: ");
						String pw = sc.next();
						boolean check = false;	
						for(int i =0; i<list.size(); i++){
						       Superuser login = list.get(i);
						       if(login.getUsername().equals(un) && login.getPassword().equals(pw)) {
						           admin = login;
						           check = true;
						       }      
						}
						if(check == true) {
							System.out.println("huzah it worked");
							runAdminApp(admin);
							break;
						}else {
							System.out.println("Credential don't match. Try again");
						}	
						
					}
					break;	

				}else {
					System.out.println("Invalid input. Try Again.");
					//user= sc.next();
				}
			}

			//sc.close();
			running = false;
		}
		
	}



	private static void runUserApp(User u) throws SQLException {
		UserDAO dao = DAOUtilities.getUserDAO();
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			System.out.println("\nSelect an option:\n"
					+ "\tView the balance on your account ----<v>\n"
					+ "\tWithdraw money from your account ----<w>\n"
					+ "\tDeposit money into your account -----<d>\n"
					+ "\tDelete your account ---------------<delete>\n"
					+ "\tLogout ----------------------------- <lo>");
			
			String action = sc.next();
			if(action.equals("w")) {
				System.out.print("How much do you want to take out?   $");
				double withdraw = sc.nextDouble();
				while(true) {
					if(withdraw>u.getAccountBal()) {
						System.out.print("Invalid amount. Please enter value < your account balance \n$");
						withdraw = sc.nextDouble();
					}else if(withdraw<0) {
						System.out.print("Invalid amount. Please enter value >= 0 \n$");
						withdraw = sc.nextDouble();
					} else {
						break;
					}
				}
				
				dao.withdrawMoney(u, withdraw);

				continue;
				
			} else if(action.equals("d")) {
				System.out.print("How much do you want to deposit?   $");
				double deposit = sc.nextDouble();
				while(deposit < 0) {
					System.out.print("Invalid amount. Please enter value >= 0 \n$");
					deposit = sc.nextDouble();
				}
				
				
				dao.depositMoney(u, deposit);
				
				continue;
				
			} else if(action.equals("v")) {
				dao.viewAcc(u);
				
				continue;
			}else if(action.equals("delete")) {
				System.out.println(u.getAccountBal());
				if(u.getAccountBal()>0) {
					System.out.println("You need to empty your account befor deleting it.");
					continue;
				}else if(u.getAccountBal()==0){
					dao.deleteAcc(u);
					break;
				}
				
			}else if(action.equals("lo")) {
				System.out.println("\n=============================================================");
				break;
			} else {
				System.out.println("Invalid input. Try Again.");
			}
		}
		
	}


	private static void runAdminApp(Superuser admin) {
		SuperuserDAO sdao = DAOUtilities.getSuperDAO();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Select an option:\n"
					+ "\tView all users info ------------------<v>\n"
					+ "\tSelect customer account to update ----<s>\n"
					+ "\tLogout to Main Menu ------------------<lo>");
			String action = sc.next();
			if(action.equals("v")) {
				try {
					sdao.viewAllUsers();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n");
				continue;
			}else if(action.equals("s")) {
				while(true) {
					System.out.print("Enter username: ");
					String un = sc.next();
					System.out.print("Enter password: ");
					String pw = sc.next();
					
					Connection connection = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					User current = new User(0,0,"","",0);
					try {
						connection = DAOUtilities.getConnection();
						String sql = "SELECT USERNAME,PASSWORD FROM USERACCOUNTS";
						ps = connection.prepareStatement(sql);
						
						rs = ps.executeQuery();
						boolean checker = false;
						while(rs.next()) {
							String username = rs.getString("USERNAME");
							String password = rs.getString("PASSWORD");
							if(username.equals(un) && password.equals(pw)) {
								checker = true;
								break;
							}
						}
						if(checker==true) {
							current = getUser(un,pw);
							System.out.println("Select an option:\n"
									+ "\tWithdraw money from account ---<w>\n"
									+ "\tDeposit money into account ----<d>\n"
									+ "\tDelete the account ----------<delete>\n"
									+ "\tBack out   --------------------<b>");
							String decision = sc.next();
							boolean check = true;
							while(check) {
								if(decision.equals("w")) {
									System.out.print("How much do you want to take out?   $");
									double withdraw = sc.nextDouble();
									while(true) {
										if(withdraw>current.getAccountBal()) {
											System.out.print("Invalid amount. Please enter value < your account "
																+ "balance \n$");
											withdraw = sc.nextDouble();
										}else if(withdraw<0) {
											System.out.print("Invalid amount. Please enter value >= 0 \n$");
											withdraw = sc.nextDouble();
										} else {
											break;
										}
									}
									sdao.takeMoney(current, withdraw);

									check = false;
								}else if(decision.equals("d")) {
									System.out.print("How much do you want to deposit?   $");
									double deposit = sc.nextDouble();
									while(deposit < 0) {
										System.out.print("Invalid amount. Please enter value >= 0 \n$");
										deposit = sc.nextDouble();
									}
									sdao.giveMoney(current, deposit);


									check = false;
								}else if(decision.equals("delete")) {
									sdao.deleteUser(current);;

									System.out.println("Customer removed from the system.");
									
									check = false;
								}else if(decision.equals("b")) {
									check = false;
								}else {
									System.out.println("Invalid input. Try again.");
									decision = sc.next();
								}
							}
						}else {
							System.out.println("Credentials didn't match. Try again");
							continue;
						}
									
					}catch(SQLException e) {
						e.printStackTrace();
					}
					
					break;
							
				}

			}else if(action.equals("lo")) {
				System.out.println("\n=============================================================");
				break;
			}else {
				System.out.println("Invalid input. Try Again.");
			}
		}
		
	}

	public static User getUser(String user, String pass) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User current = new User(0,0,"","",0);
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM USERACCOUNTS WHERE (USERNAME, PASSWORD) IN "
					+ "(SELECT USERNAME, PASSWORD FROM USERACCOUNTS WHERE USERNAME = ? AND PASSWORD =?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1,user);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				current.setUserID(rs.getInt("USERID"));
				current.setAccountID(rs.getInt("ACCOUNTID"));
				current.setUsername(rs.getString("USERNAME"));
				current.setPassword(rs.getString("PASSWORD"));
				current.setAccountBal(rs.getDouble("ACCOUNTBAL"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return current;
		
	}
	
	
}
