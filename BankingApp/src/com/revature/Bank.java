package com.revature;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList <Customers> customerInfo ;

/*
 * Read && Write employee's text file
 */
	public ArrayList <Customers> getCustomerInfo() {
		return customerInfo;
	}

	public  void writeEmployeeInfo(ArrayList <Employees> list, String user,String pass, String name) {
		try {	
			//file output
			FileOutputStream f = new FileOutputStream(new File("EmployeesInfo.txt")); 
			ObjectOutputStream o = new ObjectOutputStream(f);			
			//write original file first
			for(int i = 0; i< list.size(); i++)
			{
				o.writeObject(list.get(i));
			}
			// Write new objects to file
			Employees employeeList = new Employees(user,pass,name);
			o.writeObject(employeeList);
			//close files
			o.close();
			f.close();			
		} catch (FileNotFoundException e11) {
			System.out.println("File not found");
		} catch (IOException e1) {
			System.out.println("Error initializing stream");
		
		}

	}
	public  ArrayList <Employees> readEmployeeInfo()
	{
	    ArrayList<Employees> al = new ArrayList<Employees>();
		   boolean cont = true;
	        try {
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EmployeesInfo.txt"));
	            while(cont){
	                  Object obj=null;
	                try {
	                    obj = ois.readObject();
	                } catch (EOFException e) 
	                {
	                }
	                	catch (ClassNotFoundException e) {
	                }
	                  if(obj != null)
	                     al.add((Employees) obj);
	                  else
	                     cont = false;
	               }
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	           // e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        //    e.printStackTrace();
	        }
			return al;

	}
	
	/*
	 * Read && Write admin text file
	 */
	

	public boolean checkAdminUsername(String user,ArrayList <Administrator> adminList )
	{
		for(int i = 0; i < customerInfo.size(); i++)
		{
			if(customerInfo.get(i).getCustomerUsername() == user)
			{
				return true;
			}
		}
		
		return false;
		
	}
	public  void writeAdminInfo(ArrayList <Administrator> list, String username, String pass,String name) {
		try {
			
			//file output
			FileOutputStream f = new FileOutputStream(new File("AdminInfo.txt")); 
			ObjectOutputStream o = new ObjectOutputStream(f);			
			//write original file first
			for(int i = 0; i< list.size(); i++)
			{
				o.writeObject(list.get(i));
			}
			// Write new objects to file
			Administrator a1 = new Administrator(username,pass,name);
			o.writeObject(a1);
			//close files
			o.close();
			f.close();			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		
		}

	}
	public  ArrayList <Administrator> readAdminInfo()
	{
	    ArrayList<Administrator> al = new ArrayList<Administrator>();
		   boolean cont = true;
	        try {
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("AdminInfo.txt"));
	            while(cont){
	                  Object obj=null;
	                try {
	                    obj = ois.readObject();
	                } catch (EOFException e) 
	                {
	                }
	                	catch (ClassNotFoundException e) {
	                }
	                  if(obj != null)
	                     al.add((Administrator) obj);
	                  else
	                     cont = false;
	               }
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	         //   e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	         //   e.printStackTrace();
	        }
			return al;

	}
	/*    
	 *  Read && Write customer files
	 */
	public void setCustomerInfo(ArrayList <Customers> customerInfo) {
		this.customerInfo = customerInfo;
	}
	public boolean checkUsername(String user,ArrayList <Customers> customerInfo )
	{
		for(int i = 0; i < customerInfo.size(); i++)
		{
			if(customerInfo.get(i).getCustomerUsername() == user)
			{
				return true;
			}
		}
		
		return false;
		
	}
	public  void writeCustomerInfo(ArrayList <Customers> list, String username, String pass, String phone, String adr,String name, double amount) {
		try {
			
			//file output
			FileOutputStream f = new FileOutputStream(new File("CustomerInfo.txt")); 
			ObjectOutputStream o = new ObjectOutputStream(f);			
			//write original file first
			for(int i = 0; i< list.size(); i++)
			{
				o.writeObject(list.get(i));
			}
			// Write new objects to file
			Customers c1 = new Customers(username,pass,phone,adr,name,amount);
			o.writeObject(c1);
			//close files
			o.close();
			f.close();			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		
		}

	}
	public  ArrayList <Customers> readCustomerInfo()
	{
	    ArrayList<Customers> al = new ArrayList<Customers>();
		   boolean cont = true;
	        try {
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("CustomerInfo.txt"));
	            while(cont){
	                  Object obj=null;
	                try {
	                    obj = ois.readObject();
	                } catch (EOFException e) 
	                {
	                }
	                	catch (ClassNotFoundException e) {
	                }
	                  if(obj != null)
	                     al.add((Customers) obj);
	                  else
	                     cont = false;
	               }
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return al;

	}

}
