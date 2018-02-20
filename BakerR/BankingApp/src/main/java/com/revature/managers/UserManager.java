package com.revature.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.model.User;

public final class UserManager implements Persistent {
	
	private Map<Long, User> users;
	private static UserManager instance = null;
	
	@SuppressWarnings("unchecked")
	private void loadUsersFromFile(File f) {
		List<Object> userData = PersistenceManager.loadData(f);
		if(!userData.isEmpty()) {
			users = (Map<Long,User>)(userData.get(0));
		}
	}
	
	private UserManager() {
		if(Settings.userDataFile.exists()) {
			loadUsersFromFile(Settings.userDataFile);
		}
		
		if(users == null) {
			users = new HashMap<>();
		}
	}
	
	// Instance retrieval
	public static UserManager getInstance() {
		if(instance == null)
			instance = new UserManager();
		
		return instance;
	}
	
	// User modification operations
	public void changePassword(User usr, char[] newPassword) {
		byte[] newPassBytes = PasswordUtils.convertCharsToBytes(newPassword);
		usr.setPwHash(PasswordUtils.hashPassword(newPassBytes));
	}
	
	public void changeName(User usr, String firstName, String lastName) {
		if(firstName != null)
			usr.setFirstName(firstName);
		if(lastName != null)
			usr.setLastName(lastName);
	}
	
	
	// User management operations
	public Long generateId() {
		Set<Long> idSet = users.keySet();
		for(long id = 0; id < Long.MAX_VALUE-1; ++id) {
			if(!idSet.contains(id)) return id;
		}
		
		return null;
	}
	
	public List<User> getUsers() {
		return Collections.unmodifiableList(new ArrayList<>(users.values()));
	}
	
	public User getUser(Long id) {
		User usr = users.get(id);
		return usr;
	}
	
	public boolean registerUser(User usr) {
		if(users.containsKey(usr.getId())) {
			return false;
		} else {
			users.put(usr.getId(), usr);
			return true;
		}
	}
	
	public User removeUser(Long id) {
		return users.remove(id);
	}

	@Override
	public void save() {
		PersistenceManager.saveData(Settings.userDataFile, users);
	}
}
