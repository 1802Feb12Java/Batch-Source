package com.revature;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Database_Connection 
{
    static PreparedStatement preparedStatement=null;
    static ResultSet rs=null;
    static Connection connection = null;
    
	public static void main(String args[])
    {
        Properties prop = new Properties();
        InputStream input = null;
        String username="",pword="",url="";

        try {

            input = new FileInputStream("src/Database.properties");
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            pword=prop.getProperty("pword");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        try
        {
            System.out.println("-------- Connecting --------");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, pword);
            if (connection != null)
            {
                System.out.println("---- Connection Successful ----\n\n\n");
                try
                {
                    String selectSQL = "Select * From User_Roles";
                    preparedStatement = connection.prepareStatement(selectSQL);
                    preparedStatement.executeQuery();
                    rs = preparedStatement.executeQuery();

                    while(rs.next())
                    {
                        int userid = rs.getInt(1);
                        String role=rs.getString(2);
                        System.out.println(userid+":"+role);
                    }

                }
                catch (SQLException e)
                {
                    System.out.println("An SQL exception occurred 403");
                }

            }
            else
            {
                System.out.println("Failed to make connection!");
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found");
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 701");
        }
    }
}
