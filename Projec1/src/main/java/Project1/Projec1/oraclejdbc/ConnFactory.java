package Project1.Projec1.oraclejdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnFactory {
//	  public String dbUrl = "jdbc:oracle:thin:@bankappdatabase.cfod8be6p7l2.us-east-2.rds.amazonaws.com:1521:ORCL";
//	  public String user = "NguyenN";
//	  public String pass = "BankAppPass";
	    private static ConnFactory cf = null;
	    
	    public ConnFactory(){
	        super();
	        
	    }
	    
	    public static synchronized ConnFactory getInstance(){
	        
	        if(cf == null){
	            
	            cf = new ConnFactory();
	            
	        }
	        
	        return cf;
	        
	    }
	    
	    public Connection getConnection(){
	        
	        Connection conn = null;
	        try {
//		        String filePath = "D:\\Java\\Workspace\\Projec\\\database.properties";

	            Properties prop = new Properties();
	            prop.load(new FileReader("D:\\Java\\Workspace\\Projec1\\database.properties"));
	            Class.forName(prop.getProperty("driver"));
	            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pwd"));
	            
	            System.out.println("Connection is established");
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	                
	        return conn;
	        
	    }
	    public static void main (String[] args) {
	    	ConnFactory conn = new ConnFactory();
	    	Connection con = conn.getConnection();
	    	con.toString();
	    }
}
