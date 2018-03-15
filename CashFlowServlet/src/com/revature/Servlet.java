package com.revature;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.javafx.tools.packager.Log;



@WebServlet(description = "Cash Flow Servlet", urlPatterns = { "/CFServlet" })

public class Servlet extends HttpServlet 
{
	private static final Logger log = Logger.getLogger("Servlet");
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	
	  public void init(ServletConfig config) throws ServletException 
	  {
		    super.init(config);
		    InputStream input=null;
		    Properties prop = new Properties();
	        String url="",username="",pword="";
	        try {
	        
	        		input = getClass().getClassLoader().getResourceAsStream("Database.properties");
	      
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
	            System.out.println(url+":"+username+":"+pword);
	            connection = DriverManager.getConnection(url, username, pword);
	            if (connection != null)
	            {
	                System.out.println("---- Connection Successful ----\n\n\n");
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



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	  	
		
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		int parameter = data.get("parameter").getAsInt();
		log.info("Parameter "+parameter+" requested");
		
		HttpSession session=request.getSession(false);
		
		if(parameter==1)
		{
			System.out.println("Logged in");
			session=request.getSession(true);
			System.out.println("Session ID:"+session.getId());
			Login login=new Login();
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(login.handle(data, connection));
			
		}
		else if(parameter==9)
		{  
			System.out.println("Logged out");
			session=request.getSession(true);  
            session.invalidate();  
		}
		else
		{
	        if(session==null && parameter!=2)
	        {  
	        		response.sendRedirect("http://www.javatpoint.com");  
	        		System.out.println("Redirect");
	        		return;
	        }  
	        else
	        {   
	        		Controller control=new Controller();
	        		response.setContentType("application/json");
	    		    response.setCharacterEncoding("UTF-8");
	    		    response.getWriter().write(control.handle(data, parameter, connection));
	        }  
		}
		
		
	}
	

}
