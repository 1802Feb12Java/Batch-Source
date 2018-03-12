package database.connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		DatabaseConnect superUserObj = new DatabaseConnect();
		ArrayList <String> properties = new ArrayList<>();
		properties.add("jdbc:oracle:thin:@mydb.cfosdhdadqxy.us-east-2.rds.amazonaws.com:1521:orcl");
		properties.add("icealys");
		properties.add("jonroy84");
		try 
		{
			// write object to file
			
			FileOutputStream fos = new FileOutputStream("properties.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(properties);
			oos.close();
			// read object from file
			FileInputStream fis = new FileInputStream("properties.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			properties = (ArrayList<String>) ois.readObject();
			
			
			ois.close();

			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	      String url = properties.get(0);
	      String username = properties.get(1);
	      String password = properties.get(2);
		  Class.forName("oracle.jdbc.OracleDriver");
	      Connection con = DriverManager.getConnection(url,username,password);
	      System.out.println("Remote connection successful.");
	      Statement stmt1 = null;
	      Statement stmt2 = null;
	      try 
	      {
	      stmt1 = con.createStatement();
	      stmt2 = con.createStatement();
	      stmt1.executeQuery
	      ("CREATE SEQUENCE BANK_ACCOUNT_ID INCREMENT BY 1 START WITH 1 MAXVALUE 65535 NOCACHE NOCYCLE");
	      
	      stmt2.executeQuery
	      ("CREATE SEQUENCE USER_ID START WITH 1 INCREMENT BY 1 MAXVALUE 65535 NOCACHE NOCYCLE");
	      
	      }
	      catch(Exception e)
	      {
	    	  System.out.println("Sequences have already been created");
	      }
	      try 
	      {
	      PreparedStatement createTable = con.prepareStatement("CREATE TABLE BANK(" + "BANK_ACCOUNT_ID NUMBER," + "USER_ID NUMBER," + "USERNAME varchar2(24)," + "PASSWORD varchar2(24)," + "BALANCE NUMBER" + ")");
	      createTable.executeUpdate();
	      }
		  catch(Exception e)
		  {
			  System.out.println("Table already created");
		  }
	      superUserObj.superUser(con);
	}

}
