package JDBCBanking.JDBCBanking;

import java.sql.*;
import java.util.Scanner;

import userPackage.LoginDataNode;
import userPackage.LoginDataOps;

public class DAOClass {

	public static void AllUsers() {
		
		ConnFactory conn = new ConnFactory();
		Connection connect = conn.getConnection();
		
		try {
			
			PreparedStatement ps = connect.prepareStatement("SELECT * FROM USERS_PERSONAL");
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement p = connect.prepareStatement("SELECT * FROM USERS_ACCOUNT");
			rs = ps.executeQuery();
		} finally{
		
			return;
		}
	}
	
	public static void InsertAcc(double ACCOUNTNUM, String FULLNAME, String USERNAME, String PASS_WORD, double BALANCE, boolean JOINT) {
		
		ConnFactory conn = new ConnFactory();
		Connection connect = conn.getConnection();
		
		try {
			
			PreparedStatement ps = connect.prepareStatement("INSERT INTO USERS_ACCOUNTS\r\n" + 
					"        VALUES(?ACCOUNTNUM, ?FULLNAME, ?USERNAME, ?PASS_WORD, ?BALANCE, ?JOINT);\r\n" + 
					"");
			ResultSet rs = ps.executeQuery();
			
		} finally{
		
			return;
		}
	}
	
	public static void InsertUser(String FULLNAME, String PHONE, int AGE, String BIRTHDATE) {
		
		ConnFactory conn = new ConnFactory();
		Connection connect = conn.getConnection();
		
		try {
			
			PreparedStatement ps = connect.prepareStatement("INSERT INTO USERS_PERSONAL\r\n" + 
					"        VALUES(?FULLNAME, ?PHONE, ?AGE, ?BIRTHDATE);\r\n" + 
					"");
			ResultSet rs = ps.executeQuery();
			
		} finally{
		
			return;
		}
	}
	
	public static void DeleteAcc(double ACCNUM, String FULLNAME) {
		
		ConnFactory conn = new ConnFactory();
		Connection connect = conn.getConnection();
		
		try {
			
			PreparedStatement ps = connect.prepareStatement("DELETE FROM USERS_ACCOUNT WHERE ACCOUNTNUM = ?ACCNUM;");
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement s = connect.prepareStatement("SET @TMP = FULLNAME FROM USERS_ACCOUNT WHERE ACCOUNTNUM = ACCNUM;");
			
			PreparedStatement p = connect.prepareStatement("IF(SELECT * FROM USERS_ACCOUNT WHERE ACCOUNTNUM = ?ACCNUM, , DELETE FROM USERS_PERSONAL WHERE FULLNAME = TMP);");
			rs = p.executeQuery();
		} finally{
		
			return;
		}
	}
	
	public static void UpdatePersonal(String FULLNAME, String update) {
		
		ConnFactory conn = new ConnFactory();
		Connection connect = conn.getConnection();
		Scanner sc = new Scanner(System.in);
		
		try {
			
			if(update.equals("fullname")) {

				System.out.println("How do you want to change the name of the user?");
				String a = sc.nextLine();
				PreparedStatement ps = connect.prepareStatement("UPDATE USERS_PERSONAL SET FULLNAME = ?a WHERE FULLNAME = ?FULLNAME;");
				ResultSet rs = ps.executeQuery();
				
			}else if(update.equals("phone")) {
				
				System.out.println("How do you want to change the phone number of the user?");
				String a = sc.nextLine();
				PreparedStatement ps = connect.prepareStatement("UPDATE USERS_PERSONAL SET PHONE = ?a WHERE FULLNAME = ?FULLNAME;");
				ResultSet rs = ps.executeQuery();
				
			}else if(update.equals("age")) {
				
				System.out.println("How do you want to change the age of the user?");
				int a = sc.nextInt();
				PreparedStatement ps = connect.prepareStatement("UPDATE USERS_PERSONAL SET AGE = ?a WHERE FULLNAME = ?FULLNAME;");
				ResultSet rs = ps.executeQuery();
				
			}else if(update.equals("birthdate")) {
				
				System.out.println("How do you want to change the birthdate of the user?");
				String a = sc.nextLine();
				PreparedStatement ps = connect.prepareStatement("UPDATE USERS_PERSONAL SET BIRTHDATE = ?a WHERE FULLNAME = ?FULLNAME;");
				ResultSet rs = ps.executeQuery();
				
			}
			
		} finally{
			
			return;
		}
	}
}
