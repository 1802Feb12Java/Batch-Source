package com.revature.controllers;

import com.revature.beans.User;

public class SystemController {

	private User currentUser;
	
	public void setUser(User user) {
		this.currentUser = user;
	}
	
	public User getUser(User user) {
		return currentUser;
	}
	
}
