package pov;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import objects.RequestStatus;
import objects.RequestType;
import objects.Reimbursement;
import objects.User;

public class ReimFunc {

	public User login(String username, String password) throws SQLException, AuthenticationException{
		
		return new UserFunc().authenticate(username, password);
	}

	public static Reimbursement insertReimb(User user, double amount, RequestType type, RequestStatus status, String description) throws SQLException {
		
		return new GetItems().insertReimb(user, amount, type, status, description);
	}
	
	public static  List<Reimbursement> getReimbs(User user) throws Exception {
		
		return new GetItems().getReimbs(user);
	}

	public static List<RequestType> getTypes() throws SQLException {
		
		return new GetItems().getTypes();
	}
	
	public static List<RequestStatus> getStatus() throws SQLException {
		
		return new GetItems().getStatus();
	}

	public void updateStatus(Reimbursement reimb, User user, RequestStatus status) throws Exception {
		 
		new GetItems().updateStatus(reimb, user, status);
	}

	public Reimbursement getReimbById(int reimb_id) throws SQLException {
		
		return new GetItems().getReimbById(reimb_id);
	}
}