package com.revature.bank;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginDAO {

	public void addLogin(String u, String p, String t) throws SQLException;
	public String getLogin(String u, String p) throws SQLException;
	public void updateLogin(String u, String p) throws SQLException;
	public void deleteLogin(String username) throws SQLException;
	public ArrayList<String> getAllLogin() throws SQLException;
	
}
