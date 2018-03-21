package com.revature.main;

import java.sql.SQLException;

import com.revature.services.GuitarServices;

public class Driver {

	public static void main(String[] args) throws SQLException {
		System.out.println("main");
		GuitarServices gs=new GuitarServices();
		System.out.println(gs.getGuitar(1).toString());

	
	}
}
