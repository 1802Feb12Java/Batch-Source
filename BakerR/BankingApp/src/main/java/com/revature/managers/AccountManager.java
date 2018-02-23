package com.revature.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.model.Account;
import com.revature.model.Customer;

public final class AccountManager implements Persistent {
	private static AccountManager instance = null;
	
	private Map<Long, Account> accounts;
	private Map<Long, Account> requests;
	
	@SuppressWarnings("unchecked")
	private void loadAcctsFromFile(File f) {
		List<Object> acctDataObj = PersistenceManager.loadData(Settings.acctDataFile);
		List<Object> reqDataObj = PersistenceManager.loadData(Settings.reqDataFile);
		
		if(acctDataObj != null && !acctDataObj.isEmpty()) {
			accounts = (Map<Long, Account>)(acctDataObj.get(0));
		}
		
		if(reqDataObj != null && !reqDataObj.isEmpty()) {
			accounts = (Map<Long, Account>)(reqDataObj.get(0));
		}
		
	}
	
	private AccountManager() {
		if(Settings.acctDataFile.exists())
			loadAcctsFromFile(Settings.acctDataFile);
		
		if(accounts == null)
			accounts = new HashMap<>();
		
		
		if(Settings.reqDataFile.exists())
			loadAcctsFromFile(Settings.reqDataFile);
		
		if(requests == null)
			requests = new HashMap<>();
	}
	
	
	public static AccountManager getInstance() {
		if(instance == null)
			instance = new AccountManager();
		return instance;
	}
	
	// Account Management Operations
	public Long generateId() {
		Set<Long> acctIdSet = accounts.keySet();
		Set<Long> reqIdSet = requests.keySet();
		
		for(long id = 0; id < Long.MAX_VALUE-1; ++id) {
			if(!acctIdSet.contains(id) && !reqIdSet.contains(id)) 
				return id;
		}
		
		return null;
	}
	
		// Account Requests
	public boolean requestNewAccount(Customer... owners) {
		if(owners == null || owners.length == 0) {
			return false;
		}
		
		// Create an account request.
		Long id = generateId();
		if(id == null) return false;
		
		Account acct = new Account();
		acct.setId(id);
		for(Customer c : owners) {
			acct.getOwnerIds().add(c.getId());
		}
		requests.put(acct.getId(), acct);
		
		return true;
	}
	
	public void acceptNewAccount(Long id) {
		// Remove account from the request list
		Account acct = removeAccountRequest(id);
		
		// Get account user ids and add account to the owners' account list.
		Set<Long> acctOwnerIdSet = acct.getOwnerIds();
		acctOwnerIdSet.forEach((Long acctOwnerId) -> {
			Customer owner = (Customer)UserManager.getInstance().getUser(acctOwnerId);
			Linker.linkAccountToCustomer(owner, acct);
		});
		
		addAccount(acct);
	}
	
	public Account removeAccountRequest(Long id) {
		Account acc = requests.remove(id);
		return acc;
	}
	
	public Account getRequest(Long id) {
		return requests.get(id);
	}
	
	public List<Account> getRequests() {
		return Collections.unmodifiableList(new ArrayList<>(requests.values()));
	}
	
	
		// Accounts
	public Account getAccount(Long id) {
		return accounts.get(id);
	}
	
	public List<Account> getAccounts() {
		return Collections.unmodifiableList(new ArrayList<>(accounts.values()));
	}
	
	public boolean addAccount(Account acct) {
		if(accounts.containsKey(acct.getId()))
			return false;
		
		accounts.put(acct.getId(), acct);
		return true;
	}
	
	public Account removeAccount(Long id) {
		return accounts.remove(id);
	}
	
	// Account Operations
	public boolean withdraw(Long acctId, double amount) {
		// Get account
		Account acct = getAccount(acctId);
		
		// Can't withdraw from non-existent account.
		if(acct == null) return false;
		
		// Change withdraw amount.
		if(amount >= 0 && 
				amount <= acct.getBalance()) {
			acct.setBalance(acct.getBalance() - amount);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deposit(Long acctId, double amount) {
		Account acct = getAccount(acctId);
		
		if(acct == null) return false;
		
		if(amount < 0.0) return false;
		
		acct.setBalance(acct.getBalance() + amount);
		return true;
	}
	
	public boolean transfer(Long destId, Long srcId, double amount) {
		// Check if both accounts exist.
		if(!accounts.containsKey(destId) || !accounts.containsKey(srcId))
			return false;
		
		boolean isSuccessful = withdraw(srcId, amount);
		
		if(!isSuccessful) return false;
		
		isSuccessful = deposit(destId, amount);
		
		if(!isSuccessful) { // return money to srcAcct
			deposit(srcId, amount);
			return false;
		} else {
			return true;
		}
	}

	
	@Override
	public void save() {
		PersistenceManager.saveData(Settings.acctDataFile, accounts);
		PersistenceManager.saveData(Settings.reqDataFile, requests);
	}
}
