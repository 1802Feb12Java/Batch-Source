package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface CaveDao {

	public List<Cave> getCaves();
	public Cave getCaveById(int id);
	public int addCave(Cave c);
	public void updateCave(Cave c);
	public void deleteCave(Cave c);
}
