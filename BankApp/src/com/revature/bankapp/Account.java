package com.revature.bankapp;

public class Account {
	
	private int acctNumber;
	private String name1;//Last, First MI
	private String name2;//used if joint account
	static boolean aproved = false;//true when account is open 
	private double balance;
	
	
	public Account(int acctNumber, String name1, String name2, boolean aproved, double balance) {
		super();
		this.acctNumber = acctNumber;
		this.name1 = name1;
		this.name2 = name2;
		Account.aproved = aproved;
		this.balance = balance;
	}
	public double close()
	{
		double temp = balance;
		withdraw(balance);
		System.out.println("account closed");
		acctNumber = 0;
		name1 = "";
		name2 = "";
		aproved = false;
		balance = 0;
		return temp;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account()
	{
		super();
	}
	
	public int getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(int acctNumber) {
		this.acctNumber = acctNumber;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public boolean isAproved() {
		return aproved;
	}
	public void setAproved(boolean aproved) {
		this.aproved = aproved;
	}
	
	
	
	public double getBalance() {
		if (aproved == false)
		{
			System.out.println("not a valid account");
			return 0;
		}
		return balance;
	}
	
	//withdraw
	public double withdraw(double amount)
	{
		if (this.aproved == false)
		{
			System.out.println("not a valid account");
			return 0;
		}
		if (amount < 0)
		{
			System.out.println("you cannot withdraw a negative ammount");
			return 0;
		}
		if (amount > balance)
		{
			System.out.println("you may not ovredraw your account");
			return 0;
		}
		if (amount == 0)
		{
			System.out.println("why did you take the time to withdraw nothing?");
			return 0;
		}
		balance -= amount;
		System.out.println("I give you $"+amount);
		System.out.println("you have a remaining balance of $" + getBalance());
		return amount;
	}
	//deposit
	public void deposit(double amount)
	{
		if (aproved == false)
		{
			System.out.println("not a valid account");
			return;
		}
		if (amount < 0)
		{
			System.out.println("you cannot deposit a negative ammount");
			return;
		}
		if (amount == 0)
		{
			System.out.println("why did you take the time to deposit nothing?");
			return;
		}
		if (amount + balance > Double.MAX_VALUE)
		{
			System.out.println("your deposit is too large for the system. NO BALANCE CHANGE");
			return;
		}
		balance += amount;
		System.out.println("I take from you $"+amount);
		System.out.println("you have a total balance of $" + getBalance());
		return;
	}
	
	//transfer
	static void transfer(Account source, Account destination, double amount)
	{
		if (aproved == false)
		{
			System.out.println("not a valid account");
			return;
		}
		if (amount > source.getBalance())
		{
			System.out.println("Source account may not overdraw");
			return;
		}
		if (amount < 0)
		{
			System.out.println("you may not transfer a negative ammount");
			return;
		}
		//System.out.println(destination);
		if (amount + destination.getBalance() > Double.MAX_VALUE)
		{
			System.out.println("destination account cannot handle this tranfer");
			return;
		}
		if (amount == 0)
		{
			System.out.println("why did you take the time to transfer nothing?");
			return;
		}
		System.out.println("Source:");
		source.withdraw(amount);
		System.out.println("Destination:");
		destination.deposit(amount);
		return;		
		
	}

	@Override
	public String toString() {
		return "Account [acctNumber=" + acctNumber + ", name1=" + name1 + ", name2=" + name2 + ", aproved=" + aproved
				+ ", balance=" + balance + "]";
	}
	
	

}
