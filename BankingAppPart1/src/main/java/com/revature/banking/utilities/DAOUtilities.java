package com.revature.banking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import com.revature.banking.controller.Bank;
import com.revature.banking.dao.AccountDAO;
import com.revature.banking.dao.AdminDAO;
import com.revature.banking.dao.ApplicationDAO;
import com.revature.banking.dao.BankDAO;
import com.revature.banking.dao.CustomerDAO;
import com.revature.banking.dao.EmployeeDAO;
import com.revature.banking.dao.UserDAO;
import com.revature.banking.model.Account;
import com.revature.banking.model.Admin;
import com.revature.banking.model.Application;
import com.revature.banking.model.Customer;
import com.revature.banking.model.Employee;



public class DAOUtilities {
	public static final String bankDataFolder = "BankData";
	public static final String accountsFolder = bankDataFolder + File.separator + "Accounts";
	public static final String applicationsFolder = bankDataFolder + File.separator + "Applications";
	public static final String bankFolder = bankDataFolder + File.separator + "Bank";
	public static final String bankFile = bankFolder + File.separator+ "Bank.txt";
	public static final String usersFolder = bankDataFolder + File.separator + "User";
	public static final String customersFolder = usersFolder + File.separator + "Customers";
	public static final String employeesFolder = usersFolder + File.separator + "Employees";
	public static final String adminsFolder = usersFolder + File.separator + "Admins";
	public static final Set<String> usersFolders = new HashSet<String>(Arrays.asList(new String[]{customersFolder, employeesFolder, adminsFolder}));
	
	private static final long serialVersionUID = -6177103669279664375L;
	public static final int adminStartId =			10000000;
	public static final int employeeStartId=		20000000;
	public static final int customerStartId =		30000000;
	public static final int applicationStartId=	50000000;
	public static final int accountsStartId =		100000000;
	
	
	
	public DAOUtilities() {
		super();
	}
	public static boolean createFolders() {
		return (new File(bankDataFolder).mkdirs() &&
				new File(bankFolder).mkdirs() &&
				new File(accountsFolder).mkdirs() &&
				new File(applicationsFolder).mkdirs() &&
				new File(usersFolder).mkdirs() &&
				new File(employeesFolder).mkdirs() &&
				new File(customersFolder).mkdirs() &&
				new File(adminsFolder).mkdirs());
	}
	public static BankDAO getBankDAO() {
		return new BankDAO();
	}
	public static UserDAO getUserDAO() {
		return new UserDAO();
	}
	public static AdminDAO getAdminDAO() {
		return new AdminDAO();
	}
	public static EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAO();
	}
	public static CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}
	public static ApplicationDAO getApplicationDAO() {
		return new ApplicationDAO();
	}
	public static AccountDAO getAccountDAO() {
		return new AccountDAO();
	}
	public static Object objectRead(String filePath){
		return objectReadFile(new File(filePath));
	}
	public static Object objectReadFile(File file){
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
			System.out.println("Unable to find file " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while reading file " + file.getAbsolutePath());
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
	public static boolean objectWrite(String filePath, Object object) {
		return objectWriteFile(new File(filePath), object);
	}
	public static boolean objectWriteFile(File file, Object object) {
		if(!file.exists()) {
			try {
				if(!file.createNewFile())
					return false;
			} catch (IOException e) {
				System.out.println("Unable to create file:\t" + file.getAbsolutePath());
				e.printStackTrace();
			}finally {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		boolean success=false;
		FileOutputStream fileOutput=null;
		ObjectOutputStream  objectOut = null;
		try {
			fileOutput = new FileOutputStream(file);
			objectOut = new ObjectOutputStream(fileOutput);
			objectOut.writeObject(object);
			success=true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error writing Object to file" + file.getAbsolutePath() + "\n" 
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
		return success;
	}
	public static boolean makeNewFile(String file) {
		try {
			return new File(file).createNewFile();
		} catch (IOException e) {
			System.out.println("Error creating file:\t" + file);
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deleteObject(String fileName) {
		return new File(fileName).delete();
	}
public static boolean deleteFile(File file) {
		return file.delete();
	}
}
