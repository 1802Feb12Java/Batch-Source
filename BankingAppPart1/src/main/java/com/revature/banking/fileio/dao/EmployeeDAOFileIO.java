package com.revature.banking.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.model.Employee;
import com.revature.banking.utilities.DAOUtilities;

public class EmployeeDAO extends UserDAO implements InterfaceEmployeeDAO {

	public EmployeeDAO() {
	}
	public static Employee getEmployee(int employeeId) {
		Employee employee = null;
		employee = (Employee) DAOUtilities.objectRead(DAOUtilities.employeesFolder + File.separator + employeeId);
		return employee;
	}
	public static boolean saveEmployee(Employee employee){
		return DAOUtilities.objectWrite(DAOUtilities.employeesFolder + File.separator + employee.getUserId(), employee);
	}
	public List<Employee> getAllEmployees() {
		List <Employee> employees = new ArrayList<Employee>();
		for (File file : new File(DAOUtilities.employeesFolder).listFiles()){
			Employee employee = (Employee) DAOUtilities.objectReadFile(file);
			if (employee!=null)
				employees.add(employee);
		}
		return employees;
	}

	public Employee getEmployeeByEmployeeId(int employeeId) {
		Employee employee = EmployeeDAO.getEmployee(employeeId);
		return employee;
	}
	public boolean updateEmployee(Employee employee) {
		return EmployeeDAO.saveEmployee(employee);
	}

	public boolean addEmployee(Employee employee) {
		return EmployeeDAO.saveEmployee(employee);
	}

	public boolean deleteEmployee(Employee employee) {
		return DAOUtilities.deleteObject(DAOUtilities.employeesFolder + File.separator + employee.getUserId());
	}

	public int getNumEmployees() {
		return new File(DAOUtilities.employeesFolder).listFiles().length;
	}


}
