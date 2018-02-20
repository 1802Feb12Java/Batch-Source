package com.revature;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final long serialversionUID = 129369938L;
	ArrayList<String> accounts = new ArrayList<String>();
	
	private String currentAccount;
	
	private String id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;

	
	public Customer(String id,String firstname,String lastname,String username,String password)
	{
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}


	public static long getSerialversionuid() {
		return serialversionUID;
	}
	
}
