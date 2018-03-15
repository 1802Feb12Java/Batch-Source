package Project1.Projec1.requestdao;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import Project1.Projec1.pojo.AdminView;
import Project1.Projec1.pojo.EmployeeView;
import Project1.Projec1.pojo.ErsReimbursement;
import Project1.Projec1.pojo.Users;
import Project1.Projec1.pojo.View;


public interface RequestDao {
		public  ArrayList<ErsReimbursement> getAllReimbursement() throws SQLException;
		public  ArrayList<ErsReimbursement> getOneAllRequest(int id) throws SQLException;
		public  void editOneRequest(int id, int resolverid,Timestamp time, String type) throws SQLException;
		public boolean checkRequest(int id) throws SQLException;
		public void createReimbursement(String type, double amount, String desc, InputStream receipt, Timestamp time, int authorId  ) throws SQLException;
		public ArrayList<View> getRequestView(int uid) throws SQLException;
		
		//admin views
		public ArrayList<AdminView> getPendingRequest() throws SQLException;
		public ArrayList<AdminView> getApprovedRequest() throws SQLException;
		public ArrayList<AdminView> getDeniedRequest() throws SQLException;

		public ArrayList<AdminView> getOneRequest(int id) throws SQLException;
		public ArrayList<AdminView> getOneUserRequest(int id) throws SQLException;
		public Users getUserFirstname(int id) throws SQLException;
		public boolean searchAppId(int id) throws SQLException;
		public boolean searchAuthorId(int id) throws SQLException;
		public ArrayList<EmployeeView> getEmployee() throws SQLException;
}
