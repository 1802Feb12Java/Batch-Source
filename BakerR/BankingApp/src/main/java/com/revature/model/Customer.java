package com.revature.model;

import java.util.HashSet;
import java.util.Set;

public class Customer extends User {
	
	private static final long serialVersionUID = -6351406857983370942L;
	
	// Account Info
	private Set<Long> accountIds;
	
	
	public Customer() {
		super(0L, "", "");
		accountIds = new HashSet<>();
	}

	public Set<Long> getAccountIds() {
		return accountIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
