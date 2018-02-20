package com.revature;

import java.io.Serializable;
import java.util.ArrayList;

public class Employees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeUsername;
	private String employeePassword;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeUsername() {
		return employeeUsername;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public Employees(String employeeUsername, String employeePassword,String name) {
		super();
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.name = name;
	}
	public void applicationStatus()
	{
		
	}
	public void updateBalance()
	{
		
	}
	@Override
	public String toString() {
		return "Employees [employeeUsername=" + employeeUsername + ", employeePassword=" + employeePassword + ", name="
				+ name + "]";
	}
	public void checkAccountBalance(ArrayList <Customers> custList, Employees e)
	{
		BankInterface bi = new BankInterface();
		for(int i = 0 ; i <custList.size(); i++)
		{
			System.out.println("Name: " + custList.get(i).getName() + " balance is " + custList.get(i).getBalance());

		}
		bi.employeeSuccessfulSignUp(e);//loops back
	}
	public void checkPersonalInformation(ArrayList <Customers> custList,Employees e)
	{		
		BankInterface bi = new BankInterface();

		for(int i = 0 ; i <custList.size(); i++)
		{
			System.out.println("Customer's name: " + custList.get( i).getName() + " address:  " + custList.get(i).getAddress()+ "Phone: " +custList.get(i).getPhone() );

		}
		bi.employeeSuccessfulSignUp(e);//loops back

	}
	public void checkAccountInformation(ArrayList <Customers> custList,Employees e)
	{
		BankInterface bi = new BankInterface();

		for(int i = 0 ; i <custList.size(); i++)
		{
			System.out.println("Customer's username: " + custList.get(i).getCustomerUsername() + " Password:" +custList.get(i).getCustomerPassword());

		}
		bi.employeeSuccessfulSignUp(e);//loops back

	}
}

