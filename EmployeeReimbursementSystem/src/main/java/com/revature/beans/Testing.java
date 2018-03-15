package com.revature.beans;

import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.utility.ConnectionFactory;



public class Testing {
	
	@Test
	public void testConnection() {
		assertTrue(ConnectionFactory.getInstance() != null);
	}


}
