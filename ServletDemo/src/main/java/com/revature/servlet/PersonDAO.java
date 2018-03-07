package com.revature.servlet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO implements PDAO{

	private Connection connection;
	
	public PersonDAO() throws SQLException, ClassNotFoundException {
		super();
		this.connection = ConnectionManager.getInstance().getConnection();
		
	}
	
	public void removeUser(Integer id) throws SQLException {
		
//		{call REMOVEPERSON(?)}
//		1: ID
		
		String sql = "{call REMOVEPERSON(?)";
		CallableStatement cs = connection.prepareCall(sql);
		
		cs.setInt(1, id);
		//cs.setString(2, person.getName());
		
		cs.execute();
		
	}
	
	
	public void updateUser(Integer id, String name) throws SQLException {
//		
//		{call UPDATEPERSON(?, ?)}
//		1: ID, 2: new name
		
		String sql = "{call UPDATEPERSON(?, ?)";
		CallableStatement cs = connection.prepareCall(sql);
		
		cs.setInt(1, id);
		cs.setString(2, name);
		
		cs.execute();
		
	}

	public String getNameFromDB(Integer person) throws SQLException {
		
		String sql = "SELECT NAME FROM FRONT2BACK WHERE ID=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, person);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getString(1);
		}
		
		return "Error: Something bad happened";
	}

	public void addPersonToDB(Person person) throws SQLException {
		
//		ADDPERSON(?, ?)
//		1: id, 2: name
		
		String sql = "{call ADDPERSON(?, ?)";
		CallableStatement cs = connection.prepareCall(sql);
		
		cs.setInt(1, person.getId());
		cs.setString(2, person.getName());
		
		cs.execute();
		
		
	}
	
	
	
	
}
