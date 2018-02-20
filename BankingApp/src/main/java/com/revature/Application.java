package com.revature;

import java.io.Serializable;

public class Application implements Serializable{

	private static final long serialVersionUID = 5924599418173750115L;
	//account info to be open
	private Account account;
	//1:  approved
	//0:  pending
	//-1: denied
	private int status;
	private String applicationNumber;
	
	Application(){
		this.account = new Account();
		this.status = 0;
		this.applicationNumber = "0";
	}
	
	Application(Account a,int stat, String appNum){
		this.account = a;
		this.status = stat;
		this.applicationNumber = appNum;
	}
	
	//Getters
	public Account getAccount() {
		return account;
	}
	public int getStatus() {
		return status;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}

	//Setters
	public void setStatus(int status) {
		this.status = status;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@Override
	public String toString() {
		StringBuilder buff = new StringBuilder();
		buff.append("Application Number: " + applicationNumber + 
					"\nAccount: " + account.toString());
		switch(status) {
		case 1:
			buff.append("Status: Approved\n");
			break;
		case 0:
			buff.append("Status: Pending\n");
			break;
		case (-1):
			buff.append("Status: Denied\n");
			break;
		}
		return buff.toString();
	}
	
	
}
