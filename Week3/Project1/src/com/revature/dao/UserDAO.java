package com.revature.dao;

import com.revature.bean.User;

public interface UserDAO {
	//Create
	void addUser();
	
	//Read
	User getUser();
	
	//Update
	void setUser();
	
	//Delete
	void deleteUser();
}