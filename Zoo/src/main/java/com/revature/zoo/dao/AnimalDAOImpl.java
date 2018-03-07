package com.revature.zoo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.zoo.model.Animal;

public class AnimalDAOImpl implements AnimalDAO{

	public boolean addAnimal(Animal a) throws SQLException {
		boolean success= false;
		Connection connection = null;
		CallableStatement ps = null;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "{CALL insert_procedure (?,?)}";
			ps = connection.prepareCall(sql);
			ps.setString(1, a.getName());
			ps.setString(2, a.getSpecies());
			ps.execute();
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	public List<Animal> viewAnimal() throws SQLException {
		ArrayList<Animal> animals = new ArrayList<Animal>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM ANIMAL";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Animal a = new Animal();
				a.setAnimalID(rs.getInt("ANIMAL_ID"));
				a.setName(rs.getString("ANIMAL_NAME"));
				a.setSpecies(rs.getString("ANIMAL_SPECIES"));
				animals.add(a);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return animals;
	}
	public boolean updateAnimal(Animal animal) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
	
		ResultSet rs = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMAL SET ANIMAL_NAME= ?, ANIMAL_SPECIES=? WHERE ANIMAL_ID=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, animal.getName());
			ps.setString(2, animal.getSpecies());
			ps.setInt(3, animal.getAnimalID());
			if(ps.executeUpdate()!=0)
				return true;
			else 
				return false;			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	public boolean deleteAnimal(Animal animal) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
	
		ResultSet rs = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE ANIMAL WHERE ANIMAL_ID=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, animal.getAnimalID());
			if(ps.executeUpdate()!=0)
				return true;
			else 
				return false;			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}