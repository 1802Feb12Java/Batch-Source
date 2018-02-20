package com.revature.banking.part1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManager {

	public DataManager() {
		// TODO Auto-generated constructor stub
	}
	public static Bank getBank() {
		Bank bank = null;
		bank = (Bank) objectRead("Bank"+File.separator+"Bank");
		return bank;
	}
	public static void saveBankData(Bank bank) {
		objectWrite("Bank"+File.separator+"Bank",bank);
	}
	public static Account getAccount(int accountNumber) {
		Account account = null;
		account = (Account) objectRead("Account" + File.separator + accountNumber);
		return account;
	}
	public static void saveAccount(Account account) {
		objectWrite("Account" + File.separator + account.getAccountNumber(), account);		
	}
	public static void saveApplication(Application app) {
		objectWrite("Application" + File.separator + app.getApplicationID(), app);
	}
	public static Application getApplication(int applicationNumber) {
		Application app = null;
		app = (Application) objectRead("Application" + File.separator + applicationNumber);
		return app;
	}
	public static Admin getAdmin(int adminID) {
		Admin admin = null;
		admin = (Admin) objectRead("Admin" + File.separator + adminID);
		return admin;
	}
	public static void saveAdmin(Admin admin) {
		objectWrite("Admin" + File.separator + admin.getID(), admin);
	}
	public static Employee getEmployee(int employeeID) {
		Employee employee = null;
		employee = (Employee) objectRead("Employee" + File.separator + employeeID);
		return employee;
	}
	public static void saveEmployee(Employee employee){
		objectWrite("Employee" + File.separator + employee.getID(), employee);
	}
	public static Customer getCustomer(int customerID) {
		Customer customer = null;
		customer = (Customer) objectRead("Customer" + File.separator + customerID);
		return customer;
	}
	public static void saveCustomer(Customer customer) {
		objectWrite("Customer" + File.separator + customer.getID(), customer);
	}
	private static Object objectRead(String filePath){
		File file = new File(filePath);
		Object object = null;
		if (!file.exists()) {
		     return object;
		  }
		FileInputStream fileInput = null;
		ObjectInputStream  objectIn= null;
		try {
			fileInput = new FileInputStream(file);
			objectIn = new ObjectInputStream(fileInput);
			object = objectIn.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file " + filePath);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while reading file " + filePath);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Unable to read object");
		}finally {
			try {
				if(objectIn!=null)
					objectIn.close();
				if(fileInput!=null)
					fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error in closing the Stream");
			}
		}
		return object;
	}
	private static void objectWrite(String filePath, Object object) {
		File file = new File(filePath);
		FileOutputStream fileOutput=null;
		ObjectOutputStream  objectOut = null;
		try {
			fileOutput = new FileOutputStream(file);
			//file.getParentFile().mkdirs();
			objectOut = new ObjectOutputStream(fileOutput);
			objectOut.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error writing Object to file" + filePath + "\n" 
								+ object.toString());
		}finally {
			try {
				if(objectOut!=null)
					objectOut.close();
				if(fileOutput!=null)
					fileOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error in closing the Stream");
			}
		}
	}
}
