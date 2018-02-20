package com.revature.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Account implements Serializable {
	private static final long serialVersionUID = 4686839601360470717L;
	
	private Long id; // Account ID
	private Set<Long> ownerIds; // Account Owner Ids
	private double balance; // Account balance.
	
	public Account() {
		id = 0L;
		ownerIds = new HashSet<>();
		balance = 0.0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Long> getOwnerIds() {
		return ownerIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
