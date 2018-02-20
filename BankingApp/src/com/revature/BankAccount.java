package com.revature;

import java.io.Serializable;
import java.util.ArrayList;

public class BankAccount implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long serialversionUID = 129349938L;
	double checking = 0;
	double savings = 0;
	double id = 1;
	ArrayList<Integer> customersID = new ArrayList<Integer>();
	String username;
	String password;
	
	boolean withdrawChecking(double amnt)
	{
		//validate that they have enough money 
		//if(amnt > 0)
		if(checking <=0 || amnt > checking)
		{
			System.out.println("You only have " + checking + " in your account.");
			return false;
		}
		else
		{
			System.out.println("You have withdrawn " + amnt + " amount. Thank you");
			checking -= amnt;
			return true;
		}
	}
	
	boolean depositChecking(double amnt)
	{
		if(amnt>0)
		{
			System.out.println("You have deposited " + amnt + " .Thanks");
			checking += amnt;
			return true;
		}
		else
		{
			System.out.println("Deposit failed. insufficient amount entered");
			return false;
		}
	}
	
	boolean withdrawSavings(double amnt)
	{
		//validate that they have enough money 
		//if(amnt > 0)
		if(checking <=0 || amnt > checking)
		{
			System.out.println("You only have " + checking + " in your account.");
			return false;
		}
		else
		{
			System.out.println("You have withdrawn " + amnt + " amount. Thank you");
			checking -= amnt;
			return true;
		}
	}
	
	boolean depositSavings(double amnt)
	{
		if(amnt>0)
		{
			System.out.println("You have deposited " + amnt + " .Thanks");
			checking += amnt;
			return true;
		}
		
		else
			
		{
			System.out.println("Deposit failed. insufficient amount entered");
			return false;
		}
	}

	public static long getSerialversionuid() {
		return serialversionUID;
	}
}
