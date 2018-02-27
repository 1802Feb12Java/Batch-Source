package com.revature.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.services.UserServices;

public class Driver 
{
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException
    {
    		// insert example
//    		UserServices userDao = new UserServices();
//    		userDao.insertNewUser(3, "Sonam", "asdjfklasd", "bbob@gmail.com", "2394023904", "bobbo", "passfda");
    		
    		// Instantiate menu object
    		Menu menu = new Menu();
    		
    		// Instantiate Core object
    		Core core = new Core();
    	
	    	// Instantiate new Scanner object
	    	Scanner sc = new Scanner(System.in);
	    	
	    	// Set boolean exit variable
	    	boolean exit = false;
	    	
	    	// Perform function while session is active
	    	while(!exit) {
	    		
	    		// Starting screen
	    		menu.mainMenu();
	    		
	    		// Get user input
	    		int choice = sc.nextInt();
	    		
	    		switch(choice) {
	    			case 1:
	    				core.adminLogin();
	    				break;
	    				
	    			case 2:
	    				core.userAccountFlow();
	    				break;
	    				
	    			case 3:
	    				core.register();
	    				break;
	    				
	    			case 4:
	    				exit = true;
	    				break;
	    				
	    			default:
	    				System.out.println("Error! Please enter a 1, 2, 3 or 4");
	    				break;
	    		}
	    		
		
	    	}
		
	    
		
		sc.close();
    }
}
