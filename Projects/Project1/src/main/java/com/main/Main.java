package com.main;

import java.sql.SQLException;

import com.revature.controllers.User;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println(User.viewReimbursementRequests("e1"));

	}

}