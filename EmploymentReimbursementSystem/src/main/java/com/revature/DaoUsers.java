package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoUsers {
	String username;
	String password;
	String firstname;
	String lastname;
	String email;
	String amount;
	String description;
	String blob;
	String status;
	public DaoUsers() {
		// TODO Auto-generated constructor stub
	}
	public DaoUsers(String amount, String desc, String blob, String status)
	{
		this.amount = amount;
		this.description = desc;
		this.blob = blob;
		this.status = status;
	}
	public DaoUsers(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public DaoUsers(String firstname, String lastname, String status)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.status = status;
		
	}
	public DaoUsers(String username, String password, String firstname, String lastname, String email)
	{
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	public Connection connectionFactory()
	{
		Connection con = null;
		ArrayList <String> properties = new ArrayList<String>();
		properties.add("jdbc:oracle:thin:@mydb.cfosdhdadqxy.us-east-2.rds.amazonaws.com:1521:orcl");
		properties.add("icealys");
		properties.add("jonroy84");
		String url = properties.get(0);
	      String username = properties.get(1);
	      String password = properties.get(2);
		  try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("Remote connection successful.");
	      return con;
	}
	public ResultSet sendRe(Connection con, String usernameCk, String passwordCk) throws SQLException
	{
		ResultSet rs = null;
		//get cookies
		
		PreparedStatement pst = con.prepareStatement
		("UPDATE ERS_REIMBURSEMENTS"
		 +"SET R_ID = ?, R_AMOUNT = ?, R_DESCRIPTION = ?, R_RECEIPT = ?"
		 +"RS_STATUS = ?, U_ID_RESOLVER = ?, RS_RE_ID = ?"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?"
		 +"AND ERS_USER.U_ID = ERS_REIMBURSEMENTS.U_RE_ID"
		 +"AND ERS_RE_STATUS.RS_ID = ERS_REIMBURSEMENTS.RS_RE_ID");
		pst.setString(1, "R_ID.NEXTVAL");
		pst.setString(2, this.amount);
		pst.setString(3, this.description);
		pst.setString(4, this.blob);
		pst.setString(5, this.status);
		pst.setString(6, "U_ID_RESOLVER.NEXTVAL");
		pst.setString(7, "RS_RE_ID.NEXTVAL");
		pst.setString(8, usernameCk);
		pst.setString(9, passwordCk);
		pst.executeQuery();
		
		PreparedStatement pst2 = con.prepareStatement
		("SELECT R_DESCRIPTION, RS_STATUS"
		 +"FROM ERS_REIMBURSEMENTS"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?"
		 +"AND ERS_USER.U_ID = ERS_REIMBURSEMENTS.U_RE_ID"
		 +"AND ERS_RE_STATUS.RS_ID = ERS_REIMBURSEMENTS.RS_RE_ID");
		pst2.setString(1, username);
		pst2.setString(2, password);
		rs = pst2.executeQuery();
		return rs;
	}
	public ResultSet login(Connection con) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement pst = con.prepareStatement
		("SELECT U_USERNAME, U_PASSWORD"
		 +"FROM ERS_USERS"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?");
		pst.setString(1, this.username);
		pst.setString(2, this.password);
		rs = pst.executeQuery();
		return rs;
	}
	public void approveDenyEmp(Connection con) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement pst = con.prepareStatement
		("UPDATE ERS_RE_STATUS"
		 +"SET RS_ID = ?, RS_STATUS = ?"
		 +"WHERE U_FIRSTNAME = ? AND U_LASTNAME = ?"
		 +"AND ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_RE_ID"
		 +"AND ERS_RE_STATUS.RS_ID = ERS_REIMBURSEMENTS.RS_RE_ID");
		pst.setString(1, "RS_ID.NEXTVAL");
		pst.setString(2, this.status);
		pst.setString(3, this.firstname);
		pst.setString(4, this.lastname);
		pst.executeQuery();
	}
	public ResultSet displayEmp(Connection con) throws SQLException
	{
		PreparedStatement pst = con.prepareStatement
		("SELECT U_USERNAME, U_PASSWORD"
		 +"FROM ERS_USERS"
		 +"WHERE U_FIRSTNAME = ? AND U_LASTNAME = ?");
		pst.setString(1, this.firstname);
		pst.setString(2, this.lastname);
		ResultSet rs = null;
		rs = pst.executeQuery();
		ArrayList<String> arrayBuff = new ArrayList<String>();
		String buff;
		int i = 1;
		while(rs.next())
		{
			buff = rs.getString(i);
			arrayBuff.add(buff);
			i++;
		}
		PreparedStatement pst2 = con.prepareStatement
		("SELECT U_RE_RESOLVER, R_DESCRIPTION, R_AMOUNT, RS_STATUS"
		 +"FROM ERS_USERS"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?"
		 +"AND ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_RE_ID"
		 +"AND ERS_RE_STATUS.RS_ID = ERS_REIMBURSEMENTS.RS_RE_ID");
		pst2.setString(1, arrayBuff.get(1));
		pst2.setString(2, arrayBuff.get(2));
		rs = pst2.executeQuery();
		return rs;
	}
	public ResultSet getEmp(Connection con) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement pst = con.prepareStatement
		("SELECT U_FIRSTNAME, U_LASTNAME"
		 +"FROM ERS_USERS"
		 +"WHERE ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ROLES_ID");
		rs = pst.executeQuery();
		return rs;
	}
	public void createUser(Connection con, String usernameCk, String passwordCk) throws SQLException
	{
		//Get cookies
		PreparedStatement pst = con.prepareStatement
		("INSERT INTO ERS_USERS"
		 +"VALUES (U_ID = ?, U_USERNAME = ?, U_PASSWORD = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ?)");
		pst.setString(1, "U_ID.NEXTVAL");
		pst.setString(2, usernameCk);
		pst.setString(3, passwordCk);
		pst.setString(4, this.firstname);
		pst.setString(5, this.lastname);
		pst.setString(6, this.email);
		pst.executeQuery();
	}
	public void updateUser(Connection con, String usernameCk, String passwordCk) throws SQLException
	{
		//Get cookies
		PreparedStatement pst = con.prepareStatement
		("UPDATE ERS_USERS"
		 +"SET U_USERNAME = ?, U_PASSWORD = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ?"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?");
		pst.setString(1, this.username);
		pst.setString(2, this.password);
		pst.setString(3, this.firstname);
		pst.setString(4, this.lastname);
		pst.setString(5, this.email);
		pst.setString(6, usernameCk);
		pst.setString(7, passwordCk);
		pst.executeQuery();
	}
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlob() {
		return blob;
	}

	public void setBlob(String blob) {
		this.blob = blob;
	}

	
}
