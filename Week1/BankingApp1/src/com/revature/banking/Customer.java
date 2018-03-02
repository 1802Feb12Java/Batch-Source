package com.revature.banking;

import java.io.File;

public class Customer extends User implements Storage {

	private static final long serialVersionUID = 1L;

	public Customer(String username, String password) {
		super(username, password, 0);
	}

	public Customer(String username) {
		super(username);
	}

	public void createCustomer() {

		// create user
		Customer createNewUser = new Customer(this.getUsername(), this.getPassword());
		File checkUserExists = new File("..\\BankingApp1\\ignorethis\\users\\" + createNewUser.getUsername() + ".txt");
		// if already exists
		if (checkUserExists.isFile()) {
			System.out.println("User already exists.");
		}
		// create if doesn't exist
		else {
			createNewUser.storeFile();
			System.out.println("User created.");
		}
	}

	public void withdraw(float amount) {

		if (amount <= this.getBalance() && amount > 0) {
			this.setBalance(this.getBalance() - amount);
		} else {
			System.out.println("Insufficient funds or input");
		}

		// store transaction to file
		this.storeFile();
	}

	public void deposit(float amount) {
		float currentBalance = this.getBalance();
		float finalBalance = amount + currentBalance;
		this.setBalance(finalBalance);

		// store transaction to file
		this.storeFile();
	}

	public void transfer(String toUser, float amount) {
		File file = new File("..\\BankingApp1\\ignorethis\\users\\" + toUser + ".txt");

		if (amount < 0) {
			System.out.println("Please enter valid amount");
		} else {
			if (file.isFile()) {
				User forTransfer = new User(toUser);
				forTransfer = forTransfer.getUser1(toUser);

				if (this.getBalance() >= amount) {
					((Customer) this).withdraw(amount);
					((Customer) forTransfer).deposit(amount);
				} else {
					System.out.println("Insufficient funds");
				}
			} else {
				System.out.println("Recipient does not exist");
			}
		}
	}
}