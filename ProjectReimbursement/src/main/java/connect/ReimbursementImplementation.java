package connect;

import java.sql.Connection;
import java.sql.SQLException;

import objects.Reimbursement;
import objects.User;

public class ReimbursementImplementation {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ConnFactory connect = new ConnFactory();
		Connection conn = connect.getConnection();
			
				// Test ReimbursementDAO
				ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
				
				/* Insert new Reimbursement
				Reimbursement reimb = new Reimbursement(5, 100.00,null,null,null,,null,null,null);
				*/
				//Reimbursement reimb = new Reimbursement(2, 5, null, null,null,null,null,null,null);
				/*UserDAO userDao = new UserDAO(conn);
				User user= new User();
				user = userDao.getUserLoginInfo("jane");
				System.out.println("ReimbDaoImpl: User Id for getUserLoginInfo" + user.getUser_id());
				
				ReimbType type = reimbDao.getTypeByID(1);
				ReimbStatus status = reimbDao.getStatusByID(1);
				
				reimbDao.insertReimb(user, 100.0, type, status, "testing");*/
				
				
				//reimbDao.getAllReimbs();
				//System.out.println("All reimbs size: " +reimbDao.getAllReimbs().size());
				//reimbDao.getReimbByAuthor(1);
				reimbDao.getReimbByStatus("Approved");
				
				// Test that statuses and types are being retrieved from the database
				//reimbDao.getStatus();
				//reimbDao.getTypes();
			
				conn.close();
				System.out.println("Finished");
			}
	}