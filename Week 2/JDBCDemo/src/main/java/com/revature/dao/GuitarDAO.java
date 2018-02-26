package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Guitar;

public interface GuitarDAO {
	//CRUD operations
	public void addGuitar();
	public Guitar getGuitar(int id)throws SQLException;
	public void updateGuitar(Guitar g)throws SQLException;
	public void deleteGuitar(Guitar g)throws SQLException;
	public List<Guitar> getAllGuitars()throws SQLException;
}
