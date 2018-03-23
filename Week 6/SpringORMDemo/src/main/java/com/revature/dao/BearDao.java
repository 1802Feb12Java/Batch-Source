package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface BearDao {
	
	public List<Bear> getBears();
	public Bear getBearById(int id);
	public int addBear(Bear b);
	public void updateBear(Bear b);
	public void deleteBear(Bear b);
}