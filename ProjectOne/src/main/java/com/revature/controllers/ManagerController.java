package com.revature.controllers;

import java.util.ArrayList;

import com.revature.beans.Employee;

public class ManagerController extends EmployeeController {

	
	public void createEmployeeAccount(String username, String password, String email) {
		
		Employee newEmp = new Employee(username, password, email);
		
	}
	
	public ArrayList<Employee> viewEmployees(){
		
		ArrayList<Employee> eList = new ArrayList<>();
		
		//TODO: call DAO to get employee data
		
		return eList;
		
		
	}
	
}
