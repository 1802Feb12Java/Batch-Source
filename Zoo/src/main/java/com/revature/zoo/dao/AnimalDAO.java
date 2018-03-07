package com.revature.zoo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.zoo.model.Animal;

public interface AnimalDAO {

	boolean addAnimal(Animal a) throws SQLException;
	List<Animal> viewAnimal() throws SQLException;
}
