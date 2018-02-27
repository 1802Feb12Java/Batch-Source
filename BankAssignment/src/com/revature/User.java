package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User implements UserDAO{
	private int userid;
	private String username;
	private String password;
	private String superuser;
	
	public User(int userid, String username, String password, String superuser) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.superuser = superuser;
		
	}
	
	@Override
	public void createUser(Connection c, String Username, String Password) throws SQLException {
		
		
		PreparedStatement stmt = c.prepareStatement("insert into usertable values (?,?,?,?)");
		PreparedStatement id = c.prepareStatement("SELECT USERIDSEQ.NEXTVAL FROM DUAL");
		ResultSet rs = id.executeQuery();
		int myId = 0;
		if(rs.next())
			myId = rs.getInt(1);
				stmt.setInt(1, myId);
				stmt.setString(2, Username);
				stmt.setString(3, Password);
				stmt.setString(4,  "N");
	            stmt.executeQuery();
	        
		}
	

	@Override
	public void createSuperUser(Connection c, String Username, String Password) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("insert into usertable values (?,?,?,?)");
		PreparedStatement id = c.prepareStatement("SELECT USERIDSEQ.NEXTVAL FROM DUAL");
		ResultSet rs = id.executeQuery();
		int myId = 0;
		if(rs.next())
			myId = rs.getInt(1);
				stmt.setInt(1, myId);
				stmt.setString(2, Username);
				stmt.setString(3, Password);
				stmt.setString(4,  "Y");
	            stmt.executeQuery();
	        
	}

	@Override
	public void updateUsername(Connection c, User loggedIn, Scanner sc) throws SQLException {
		System.out.println("What is the new username:");
		String newUsername = sc.nextLine();

		PreparedStatement stmt = c.prepareStatement("update usertable set username = (?) where userid = (?) ");
		stmt.setString(1, newUsername);
		stmt.setInt(2, this.userid);
		stmt.executeQuery();
		

		
		
	}
	@Override
	public void updatePassword(Connection c, User loggedIn, Scanner sc) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public static void superUser(Connection c, Scanner sc) throws SQLException
	{
		System.out.println("Hello Goblin. What to do?");
		System.out.println("1) Check users");
		System.out.println("2) Edit User");
		System.out.println("3) Delete User");
		System.out.println("4) Check accounts");
		System.out.println("5) Edit account");
		System.out.println("6) Delete account");
		System.out.println("7) Cancel");
		System.out.println("You will be logged out after this action.");
		int choice = sc.nextInt();
		sc.nextLine();
		int temp;
		String Temp;
		String Temp2;
		switch (choice)
		{
		case 1:	PreparedStatement stmt = c.prepareStatement("select * from usertable order by userid desc");
				ResultSet rs = stmt.executeQuery();
				while (rs.next())
				{
					System.out.println("ID\tUsername\tPassword\tSuperuser");
					System.out.print(rs.getInt(1));
					System.out.print("\t");
					System.out.print(rs.getString(2));
					System.out.print("\t\t");
					System.out.print(rs.getString(3));
					System.out.print("\t");
					System.out.print(rs.getString(4));
					System.out.println();	
				}
				break;
		case 2: 
				PreparedStatement updateU = c.prepareStatement("Update Usertable set ? = ? where userID = ?");
				System.out.println("update what column?");
				Temp = sc.next();
				sc.nextLine();
				updateU.setString(1, Temp);
				System.out.println("new value");
				Temp2 = sc.next();
				updateU.setString(2, Temp2);
				sc.nextLine();
				System.out.println("UserId?");
				temp = sc.nextInt();
				sc.nextLine();
				updateU.setInt(3,temp);
				updateU.executeQuery();
				System.out.println("done.");
				break;

		case 3: //delete user
				PreparedStatement deleteU = c.prepareStatement("delete from usertable where userID = ?");
				System.out.println("delete what userid");
				temp = sc.nextInt();
				sc.nextLine();
				deleteU.setInt(1, temp);
				deleteU.executeQuery();
				System.out.println("deleted");
				break;
			
		case 4://check accounts
			PreparedStatement checkAccts = c.prepareStatement("select * from accounts order by Accountnumber desc");
			ResultSet checkAcct = checkAccts.executeQuery();
			while (checkAcct.next())
			{
				System.out.println("Acct num\tID\tBalance");
				System.out.print(checkAcct.getInt(1));
				System.out.print("\t\t");
				System.out.print(checkAcct.getInt(2));
				System.out.print("\t\t");
				System.out.print(checkAcct.getDouble(3));
				System.out.print("\t");
				System.out.println();	
			}
			
			break;
			
		case 5: //edit account--needs work
			PreparedStatement updateA = c.prepareStatement("Update Accounts set balance = ? where accountnumber = ?");
			
			System.out.println("new value");
			Double dtemp = sc.nextDouble();
			updateA.setDouble(2, dtemp);
			sc.nextLine();
			System.out.println("account number?");
			temp = sc.nextInt();
			sc.nextLine();
			updateA.setInt(3,temp);
			updateA.executeQuery();
			System.out.println("done.");
			break;
			
			
		case 6: //delete account
			PreparedStatement deleteA = c.prepareStatement("delete from Accounts where accountnumber = ?");
			System.out.println("delete what accountnumber");
			temp = sc.nextInt();
			sc.nextLine();
			deleteA.setInt(1, temp);
			deleteA.executeQuery();
			System.out.println("deleted");
			break;
			
			
		case 7: //cancel
			break;

		default: System.out.println("Invalid option. Goodbye");
		}
		
	}
	@Override
	public User login(Connection c, String username, String password, Scanner sc) throws SQLException {
		// TODO Auto-generated method stub
		if(username.equals("Griphook") && password.equals("DaGoblin"))
		{
			superUser(c, sc);
		}
		PreparedStatement stmt = c.prepareStatement("select * from usertable");
		ResultSet rs = stmt.executeQuery();
		
		
		while(rs.next())
		{
			if (username.equals(rs.getString(2)))//if username exists, check password
			{
				if (password.equals(rs.getString(3)))//if password match, fill user data with data from table
					//fill from db
					
					return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					//return
			}
		}
		//else print bad username and return null
		System.out.println("Invalid username");
		return null;
	}
	@Override
	public void logout() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void viewUser(Connection c) throws SQLException {
		System.out.println(this.toString());
		//account.tostring...
		
	}
	@Override
	public void deleteUser(Connection c, int userid) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("select * from accounts where accounts.userid = ?");
		stmt.setInt(1, this.userid);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			if (rs.getDouble(3) != 0)
			{
				System.out.println("You may not close an account with funds in it.");
				return;
			}
			else
			{
				PreparedStatement s = c.prepareStatement("update accounts set userid = -1 where userid = ?");
				s.setInt(1, userid);
				s.executeQuery();
			}
				
			
		}
		
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSuperuser() {
		return superuser;
	}
	public void setSuperuser(String superuser) {
		this.superuser = superuser;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", superuser="
				+ superuser + "]";
	}
	
	
	
}
