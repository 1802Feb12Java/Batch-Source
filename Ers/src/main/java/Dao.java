package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {
	String a;
	String b;
	String c;
	String d;
	String e;
	String amount;
	String description;
	String blob;
	String status;
	public Dao() {
		// TODO Auto-generated constructor stub
	}
	public Dao(String amount, String desc, String blob, String status)
	{
		this.amount = amount;
		this.description = desc;
		this.blob = blob;
		this.status = status;
	}
	public Dao(String a, String b)
	{
		this.a = a;
		this.b = b;
	}
	public Dao(String firstname, String lastname, String c)
	{
		this.a = firstname;
		this.b = lastname;
		this.c = c;
		
	}
	public Dao(String username, String password, String firstname, String lastname, String email)
	{
		this.a = username;
		this.b = password;
		this.c = firstname;
		this.d = lastname;
		this.e = email;
	}
	public void connectionFactory()
	{
		ArrayList <String> properties = new ArrayList<>();
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
			Connection con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("Remote connection successful.");
	}
	public ResultSet sendRe(Connection con, String username, String password) throws SQLException
	{
		//get cookies
		ResultSet rs = null;
		
		PreparedStatement pst = con.prepareStatement
		("UPDATE ERS_REIMBURSEMENTS"
		 +"SET R_ID = ?, R_AMOUNT = ?, R_DESCRIPTION = ?, R_RECEIPT = ?"
		 +"RS_STATUS = ?, U_ID_RESOLVER = ?, RS_RE_ID = ?"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?"
		 +"AND ERS_USER.U_ID = ERS_REIMBURSEMENTS.U_RE_ID");
		pst.setString(1, "R_ID.NEXTVAL");
		pst.setString(2, this.amount);
		pst.setString(3, this.description);
		pst.setString(4, this.blob);
		pst.setString(5, this.c);
		pst.setString(6, "U_ID_RESOLVER.NEXTVAL");
		pst.setString(7, "RS_RE_ID.NEXTVAL");
		pst.setString(8, username);
		pst.setString(9, password);
		pst.executeQuery();
		
		PreparedStatement pst2 = con.prepareStatement
		("SELECT R_DESCRIPTION, RS_STATUS"
		 +"FROM ERS_REIMBURSEMENTS"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?"
		 +"AND ERS_USER.U_ID = ERS_REIMBURSEMENTS.U_RE_ID"
		 +"AND ERS_RE_STATUS.RS_ID = ERS_REIMBURSEMENTS.RS_RE_ID");
		pst2.setString(1, username);
		pst2.setString(2, password);
		return rs = pst2.executeQuery();
	}
	public ResultSet login(Connection con) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement pst = con.prepareStatement
		("SELECT U_USERNAME, U_PASSWORD"
		 +"FROM ERS_USERS"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?");
		pst.setString(1, this.a);
		pst.setString(2, this.b);
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
		pst.setString(2, this.c);
		pst.setString(3, this.a);
		pst.setString(4, this.b);
		pst.executeQuery();
	}
	public ResultSet displayEmp(Connection con) throws SQLException
	{
		PreparedStatement pst = con.prepareStatement
		("SELECT U_USERNAME, U_PASSWORD"
		 +"FROM ERS_USERS"
		 +"WHERE U_FIRSTNAME = ? AND U_LASTNAME = ?");
		pst.setString(1, this.a);
		pst.setString(2, this.b);
		ResultSet rs = null;
		rs = pst.executeQuery();
		ArrayList<String> arrayBuff = new ArrayList<>();
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
		return rs = pst2.executeQuery();
		
	}
	public ResultSet getEmp(Connection con) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement pst = con.prepareStatement
		("SELECT U_FIRSTNAME, U_LASTNAME"
		 +"FROM ERS_USERS"
		 +"WHERE ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ROLES_ID");
		return rs = pst.executeQuery();
	}
	public void createUser(Connection con, String username, String password) throws SQLException
	{
		//Get cookies
		PreparedStatement pst = con.prepareStatement
		("INSERT INTO ERS_USERS"
		 +"VALUES (U_ID = ?, U_USERNAME = ?, U_PASSWORD = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ?)");
		pst.setString(1, "U_ID.NEXTVAL");
		pst.setString(2, username);
		pst.setString(3, password);
		pst.setString(4, this.a);
		pst.setString(5, this.b);
		pst.setString(6, this.c);
		pst.executeQuery();
	}
	public void updateUser(Connection con, String username, String password) throws SQLException
	{
		//Get cookies
		PreparedStatement pst = con.prepareStatement
		("UPDATE ERS_USERS"
		 +"SET U_USERNAME = ?, U_PASSWORD = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ?"
		 +"WHERE U_USERNAME = ? AND U_PASSWORD = ?");
		pst.setString(1, this.a);
		pst.setString(2, this.b);
		pst.setString(3, this.c);
		pst.setString(4, this.d);
		pst.setString(5, this.e);
		pst.setString(6, username);
		pst.setString(7, password);
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
