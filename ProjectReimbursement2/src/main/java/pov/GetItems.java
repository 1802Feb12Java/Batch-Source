package pov;

import java.sql.SQLException;
import java.util.List;

import DAO.DAO;
import objects.RequestStatus;
import objects.RequestType;
import objects.Reimbursement;
import objects.User;

public class GetItems {
	
	public List<RequestType> getTypes() throws SQLException {

		return new DAO().getTypes();
	}
	
	public List<RequestStatus> getStatus() throws SQLException {
		
		return new DAO().getStatus();
	}

	public void updateStatus (Reimbursement reimb, User user, RequestStatus status) throws Exception {
		
		new DAO().updateStatus(reimb, user, status);
	}
	
	public Reimbursement insertReimb(User author, double amount, RequestType type,RequestStatus status, String description) throws SQLException{
		
		return new DAO().insertRequest(author, amount, type, status, description);
	}	

	public List<Reimbursement> getReimbs(User user) throws Exception {
		
		if(user.getRole_id().getUser_role().equals("Manager"))
			
			return getAllReimbs();
		
		return getAuthorReimbs(user.getUser_id());
	}
		
	private List<Reimbursement> getAllReimbs() throws Exception{
		
		return  DAO.getAllReimbs();
	}

	private List<Reimbursement> getAuthorReimbs(int author_id) throws Exception{
		
		return DAO.getReimbByAuthor(author_id);
	}

	Reimbursement getReimbById(int reimb_id) throws SQLException {
		
		return DAO.getRequestById(reimb_id);
	}
}