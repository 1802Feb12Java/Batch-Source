package com.revature.servlet;

import java.sql.SQLException;

public interface PDAO {

	public String getNameFromDB(Integer person) throws SQLException;
		
	public void addPersonToDB(Person person) throws SQLException;
	
}

