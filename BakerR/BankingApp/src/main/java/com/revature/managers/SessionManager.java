package com.revature.managers;

import com.revature.model.User;

public final class SessionManager {
	private static SessionManager instance = null;
	
	private User currentUser;
	
	
	private SessionManager() {
		currentUser = null;
	}
	
	public static SessionManager getInstance() {
		if(instance == null)
			instance = new SessionManager();
		
		return instance;
	}
	
	public boolean hasActiveSession() {
		return currentUser != null;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public boolean login(User usr) {
		if(currentUser == null) { 
			currentUser = usr;
			return true;
		}
		
		return false;
	}
	
	public boolean logout() {
		if(currentUser != null) {
			currentUser = null;
			AccountManager.getInstance().save();
			UserManager.getInstance().save();
			return true;
		}
		
		return false;
	}
	
}
