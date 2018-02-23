package com.revature.database;

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
import java.util.UUID;

import com.revature.banking.BankAccount;
import com.revature.banking.Request;
import com.revature.banking.people.Person;
import com.revature.banking.people.User;

/**
 * Class used to serialize and deserialize file storage of people and accounts
 * also keeps track of file names being used
 * 
 */
public class DataStore {
	// private static final Logger logger = LogManager.getLogger(DataStore.class);

	private static final String USER_ACCOUNT_FILE = "userAccounts.txt";
	private static final String CREDENTIALS_FILE = "credentials.txt";
	private static final String ACCOUNT_APPROVE_FILE = "accountToApprove.txt";
	private static final String BANK_ACCOUNT_FILE = "bankAccounts.txt";

	public static String generateUID() {
		return UUID.randomUUID().toString();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Person> readPeopleFromFile() {
		// get the people from file
		ArrayList<Person> people = (ArrayList<Person>) readObject(USER_ACCOUNT_FILE);
		return people;
	}

	public static void writePeopleToFile(ArrayList<Person> list) {
		// write people to the file
		writeObject(list, USER_ACCOUNT_FILE);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<User> readUsersFromFile() {
		// get the people from file
		ArrayList<User> users = (ArrayList<User>) readObject(CREDENTIALS_FILE);
		return users;
	}

	public static void writeUsersToFile(ArrayList<User> list) {
		// write people to the file
		writeObject(list, CREDENTIALS_FILE);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Request> readRequestsFromFile() {
		// get the people from file
		ArrayList<Request> users = (ArrayList<Request>) readObject(ACCOUNT_APPROVE_FILE);
		return users;
	}

	public static void writeRequestsToFile(ArrayList<Request> list) {
		// write people to the file
		writeObject(list, ACCOUNT_APPROVE_FILE);
	}

	public static ArrayList<BankAccount> readBankAccountsFromFile() {
		@SuppressWarnings("unchecked")
		ArrayList<BankAccount> bankAccounts = (ArrayList<BankAccount>) readObject(BANK_ACCOUNT_FILE);
		return bankAccounts;
	}

	public static void writeBankAccountsToFile(ArrayList<BankAccount> list) {
		// write bank account info to the file
		writeObject(list, BANK_ACCOUNT_FILE);
	}

	/**
	 * Write an object to file
	 * 
	 * @param s
	 * @param filename
	 */
	private static void writeObject(Serializable s, String filename) {
		File file = new File(filename);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Reads an object from file
	 * 
	 * @param filename
	 * @return
	 */
	private static Serializable readObject(String filename) {
		Serializable s = null;
		File file = new File(filename);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			s = (Serializable) in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			// ignore
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
}
