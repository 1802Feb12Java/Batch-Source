package com.revature.implement;

import com.revature.implement.ManagerService;
import com.revature.objects.Employee;
import com.revature.objects.Reimbursement;

import java.util.List;

import com.revature.implement.EmployeeService;

public class Driver {
	public static void main(String[] args) {
		ManagerService Mdao = new ManagerService();
		EmployeeService Edao = new EmployeeService();
		
		// Creating USERS
//		Mdao.newMan("Sosherpa", "Sonam123", "Sonam", "Sherpa", "sosherpa@gmail.com");
//		Edao.newEmp("Emhart", "Emily123", "Emily", "Hart", "emhart@mail.com");
//		Edao.newEmp("Mahoney", "Matt123", "Matt", "Honey", "mahoney@mail.com");
//		Edao.newEmp("Dajohnson", "Dave123", "Dave", "Johnson", "dajohnson@mail.com");
//		Edao.newEmp("Kystuart", "Kyle123", "Kyle", "Stuart", "kystuart@mail.com");
//		Edao.newEmp("Kesmith", "Kevin123", "Kevin", "Smith", "kesmith@mail.com");
//		
		// Checking Login
//		Mdao.login("Sosherpa", "Sonam123");
//		Edao.login("blah", "blah");
//		Edao.login("Mahoney", "Matt123");
		
		// Creating Reimbursement Requests
//		Edao.reimburseReq(1000.0, "Ate out with Clients", 2, 3);
//		Edao.reimburseReq(760.0, "Brand new computers", 5, 4);
		
		// Viewin all pending Requests
//		List<Reimbursement> ReList = Mdao.pendingReq();
//		for(Reimbursement accounts : ReList){
//			System.out.println(accounts);
//		}
//		
		// Approvin/Denyin Requests
//		Mdao.approve(500, 1002);
//		Mdao.deny(500, 1003);
//		
//		// Viewin all resolved Requests
//		List<Reimbursement> ReList2 = Mdao.resolvedReq();
//		for(Reimbursement accounts : ReList2){
//			System.out.println(accounts);
//		}
//		
//		// View all employees
//		List<Employee> EmpList = Mdao.viewEmp();
//		for(Employee accounts : EmpList){
//			System.out.println(accounts);
//		}
//		
//		// View all employee request
//		List<Reimbursement> ReList3 = Mdao.empReq(2);
//		for(Reimbursement accounts : ReList3){
//			System.out.println(accounts);
//		}
//		
//		// Employee views their pending requests
//		List<Reimbursement> ReList4 = Edao.pendingReq(5);
//		for(Reimbursement accounts : ReList4){
//			System.out.println(accounts);
//		}
//		
//		// Employee views their resolved requests
//		List<Reimbursement> ReList5 = Edao.resolvedReq(5);
//		for(Reimbursement accounts : ReList5){
//			System.out.println(accounts);
//		}
//		
//		// Employee viewing their information
//		Edao.viewInfo(2);
//	}
}
}
