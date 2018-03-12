package account.features;

import java.util.ArrayList;
import java.util.Scanner;

public class Account implements java.io.Serializable {
	private ArrayList<String> userNames = new ArrayList<>();
	private String password = "";
	private int balance = 0;
	public Account() {};
	public Account(Account e)
	{
		this.userNames = e.userNames;
		this.password = e.password;
		this.balance = e.balance;
	}
	public Account withdraw(int subtract)
	{
		
		if(this.getBalance() - subtract < 0)
		{
			System.out.println("Balance Exceeded");
			return this;
			
		}
		else
		{
			int balance = this.getBalance();
			balance -= subtract;
			this.setBalance(balance);
		}
		return this;
	}
	public Account deposit(int add)
	{
		int balance = this.getBalance();
		balance += add;
		this.setBalance(balance);
		return this;
	}
	public void transfer(Account sender)
	{
		System.out.println("Enter transfer amount");
		Scanner sc = new Scanner(System.in);
		int transferAmount = sc.nextInt();
		//withdraw funds from sender
		sender.withdraw(transferAmount);
		//deposit funds to receiver
		this.deposit(transferAmount);
	}
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public ArrayList<String> getUserNames() {
		return userNames;
	}
	public void setUserNames(ArrayList<String> userNames) {
		this.userNames = userNames;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
