package com.revature.banking.part1;

public interface InterfaceAccountMod {
	public void withdraw(Account fromAccount, double amount);
	public void transfer(Account fromAccount, Account toAccount, double amount);
	public void deposit(Account toAccount, double amount);
}
