package com.revature.banking.fileio.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.fileio.model.EmployeeFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class EmployeeDAOFileIO extends UserDAOFileIO implements InterfaceEmployeeDAOFileIO {

	public EmployeeDAOFileIO() {
	}
	public static EmployeeFileIO getEmployee(int employeeId) {
		EmployeeFileIO employee = null;
		employee = (EmployeeFileIO) DAOUtilitiesFileIO.objectRead(DAOUtilitiesFileIO.employeesFolder + File.separator + employeeId);
		return employee;
	}
	public static boolean saveEmployee(EmployeeFileIO employee){
		return DAOUtilitiesFileIO.objectWrite(DAOUtilitiesFileIO.employeesFolder + File.separator + employee.getUserId(), employee);
	}
	public List<EmployeeFileIO> getAllEmployees() {
		List <EmployeeFileIO> employees = new ArrayList<EmployeeFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.employeesFolder).listFiles()){
			EmployeeFileIO employee = (EmployeeFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (employee!=null)
				employees.add(employee);
		}
		return employees;
	}

	public EmployeeFileIO getEmployeeByEmployeeId(int employeeId) {
		EmployeeFileIO employee = EmployeeDAOFileIO.getEmployee(employeeId);
		return employee;
	}
	public boolean updateEmployee(EmployeeFileIO employee) {
		return EmployeeDAOFileIO.saveEmployee(employee);
	}

	public boolean addEmployee(EmployeeFileIO employee) {
		return EmployeeDAOFileIO.saveEmployee(employee);
	}

	public boolean deleteEmployee(EmployeeFileIO employee) {
		return DAOUtilitiesFileIO.deleteObject(DAOUtilitiesFileIO.employeesFolder + File.separator + employee.getUserId());
	}

	public int getNumEmployees() {
		return new File(DAOUtilitiesFileIO.employeesFolder).listFiles().length;
	}


}
