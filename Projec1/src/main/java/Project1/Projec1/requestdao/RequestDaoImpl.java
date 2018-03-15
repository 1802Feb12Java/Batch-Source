package Project1.Projec1.requestdao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import Project1.Projec1.UserDAO.UserDaoImple;
import Project1.Projec1.oraclejdbc.ConnFactory;
import Project1.Projec1.pojo.AdminView;
import Project1.Projec1.pojo.EmployeeView;
import Project1.Projec1.pojo.ErsReimbursement;
import Project1.Projec1.pojo.Users;
import Project1.Projec1.pojo.View;

public class RequestDaoImpl implements RequestDao {
	private Logger logger = Logger.getLogger(UserDaoImple.class);

	public ArrayList<ErsReimbursement> getAllReimbursement() throws SQLException {
		
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "Select * from ers_reimbursments";

		ArrayList<ErsReimbursement> list = new ArrayList();
		
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information
		while (rs.next()) {
			logger.info("retrieving object");
			ErsReimbursement er = new ErsReimbursement();
			er.setR_amount(rs.getDouble(2));
			er.setR_description(rs.getString(3));
			er.setR_id(rs.getInt(1));
			er.setR_receipt(rs.getBlob(4));
			er.setR_submitted(rs.getDate(5));
			er.setR_resolved(rs.getDate(6));
			er.setU_id_author(rs.getInt(7));
			er.setU_id_resolver(rs.getInt(8));
			er.setRt_status(rs.getInt(9));
			er.setRt_type(rs.getInt(10));
			list.add(er);// save data in arraylist

			
		}
		//close connection
		ps.close();
		rs.close();
		con.close();
		
		return list;
	}

	public ArrayList<ErsReimbursement> getOneAllRequest(int id) throws SQLException {

		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "Select * from ers_reimbursements where u_id_author = ?";

		ArrayList<ErsReimbursement> list = new ArrayList();
		
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();//pull information
		
		while (rs.next()) {
			logger.info("retrieving object");
			ErsReimbursement er = new ErsReimbursement();
			er.setR_amount(rs.getDouble(2));
			er.setR_description(rs.getString(3));
			er.setR_id(rs.getInt(1));
			er.setR_receipt(rs.getBlob(4));
			er.setR_submitted(rs.getDate(5));
			er.setR_resolved(rs.getDate(6));
			er.setU_id_author(rs.getInt(7));
			er.setU_id_resolver(rs.getInt(8));
			er.setRt_status(rs.getInt(9));
			er.setRt_type(rs.getInt(10));
			list.add(er);// save data in arraylist

			
		}
		//close connection
		ps.close();
		rs.close();
		con.close();
		
		return list;
	}

	public void editOneRequest(int id, int resolverid,Timestamp time, String type) throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		if(type.equals("approvedRequest") || type.equals("ApprovedRequest") ) {
			
			String sql = "{call approvedRequest(?,?,?)}";
			CallableStatement stmt=con.prepareCall(sql);  
			stmt.setTimestamp(2, time);

			stmt.setInt(1, id);
			stmt.setInt(3, resolverid);

			stmt.executeUpdate();  
			stmt.close();
				
		} else {
			String sql = "{call deniedRequest(?,?,?)}";
			CallableStatement stmt=con.prepareCall(sql);  
			stmt.setTimestamp(2, time);

			stmt.setInt(1, id);
			stmt.setInt(3, resolverid);

			stmt.executeUpdate();  
			stmt.close();
		}
		con.close();	
	}
	public ArrayList<View> getRequestView(int uid) throws SQLException{
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = " select ers_reimbursments.r_id,ers_reimbursments.r_amount,ers_reimbursments.r_description, ers_reimbursments.r_submitted,\r\n" + 
				"    ers_reimbursments.r_resolved,ERS_REIMBURSMENTS.U_ID_RESOLVER , ers_reimbursment_status.rs_status,ers_reimbursement_type.rt_type from ers_reimbursments \r\n" + 
				"  full outer join ers_reimbursment_status on ers_reimbursments.rt_status = ers_reimbursment_status.rs_id\r\n" + 
				"  full outer join ers_reimbursement_type on ers_reimbursments.rt_type = ers_reimbursement_type.rt_id \r\n" + 
				"   where ers_reimbursments.u_id_author = ?";
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();//pull information
		ArrayList<View> list = new ArrayList();
		while (rs.next()) {
			View view = new View();
			view.setR_amount(rs.getDouble(2));
			view.setR_description(rs.getString(3));
			view.setR_id(rs.getInt(1));
			
			if(rs.getInt(6) == 0) {
				view.setR_resolver(0);
			}
			else {
				view.setR_resolver(rs.getInt(6));
			}
			view.setR_submitted(rs.getTimestamp(4).toString());
			view.setRt_status(rs.getString(7));
			view.setRt_type(rs.getString(8));
			if(rs.getTimestamp(5) == null)
			{
				view.setR_resolved("");
			}
			else {
				view.setR_resolved(rs.getTimestamp(5).toString());

			}
			list.add(view);
		}
		ps.close();
		con.close();
		return list;
	}

	public boolean checkRequest(int id) throws SQLException {
	
		return false;
	}

	public void createReimbursement(String type, double amount, String desc, InputStream receipt, Timestamp time, int authorId)
			throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "{call createRequest(?,?,?,?,?,?)}";
		CallableStatement stmt=con.prepareCall(sql);  
		stmt.setString(1, type);  
		stmt.setString(3, desc); 
		stmt.setDouble(2, amount);
		stmt.setBlob(4, receipt);
		stmt.setTimestamp(5, time);
		stmt.setInt(6, authorId);

		stmt.executeUpdate();  
		
		stmt.close();
		con.close();		
	}
//----------------------------------------- ADMIN VIEW FUNCTIONS--------------------------------------
	
	public ArrayList<AdminView> getDeniedRequest() throws SQLException{
		ArrayList<AdminView> list = new ArrayList();
//		
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection();
		String sql = " select ers_reimbursments.r_id,ers_reimbursments.r_amount,ers_reimbursments.r_description, ers_reimbursments.r_submitted,\r\n" + 
				"    ers_reimbursments.r_resolved,ers_reimbursments.U_id_author,ers_reimbursments.U_ID_RESOLVER , ers_reimbursment_status.rs_status,ers_reimbursement_type.rt_type,\r\n" + 
				" ers_reimbursments.R_RECEIPT\r\n" + 
				"    from ers_reimbursments \r\n" + 
				"  full outer join ers_reimbursment_status on ers_reimbursments.rt_status = ers_reimbursment_status.rs_id\r\n" + 
				"  full outer join ers_reimbursement_type on ers_reimbursments.rt_type = ers_reimbursement_type.rt_id \r\n" + 
				"   where ers_reimbursment_status.rs_status = 'Denied'";
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information

		while (rs.next()) {
			AdminView view = new AdminView();
			view.setR_amount(rs.getDouble(2));
			view.setR_description(rs.getString(3));
			view.setR_id(rs.getInt(1));
			view.setTime_submitted(rs.getTimestamp(4).toString());
		
			if(rs.getTimestamp(5) == null)
			{
				view.setTime_resolved("");
			}
			else {
				view.setTime_resolved(rs.getTimestamp(5).toString());

			}
			Users user = getUserFirstname(rs.getInt(6));
			String str = user.getU_firstname() + " " + user.getU_lastname();
			view.setAuthor(str);
			if(rs.getInt(7) == 0) {
				view.setResolverName("");
			}
			else {
				user = getUserFirstname(rs.getInt(7));
				str = user.getU_firstname() + " " + user.getU_lastname();
				view.setResolverName(str);
			}
			view.setRt_status(rs.getString(8));
			view.setRt_type(rs.getString(9));
			/////-------------- here comes blob
		
			InputStream binaryStream = rs.getBinaryStream(10);
			try {
				byte[] bytes = IOUtils.toByteArray(binaryStream);
				String encoded = Base64.getEncoder().encodeToString(bytes);
				String stri = "<img width=\"150\" height=\"150\" src= data:image/png;base64," + encoded + " >";  //converted everything to image
				view.setImage(stri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			list.add(view);
		}
		ps.close();
		con.close();
		
		return list;
	}

	public ArrayList<AdminView> getPendingRequest() throws SQLException {
		ArrayList<AdminView> list = new ArrayList();
//		
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection();
		String sql = " select ers_reimbursments.r_id,ers_reimbursments.r_amount,ers_reimbursments.r_description, ers_reimbursments.r_submitted,\r\n" + 
				"    ers_reimbursments.r_resolved,ers_reimbursments.U_id_author,ers_reimbursments.U_ID_RESOLVER , ers_reimbursment_status.rs_status,ers_reimbursement_type.rt_type,\r\n" + 
				" ers_reimbursments.R_RECEIPT\r\n" + 
				"    from ers_reimbursments \r\n" + 
				"  full outer join ers_reimbursment_status on ers_reimbursments.rt_status = ers_reimbursment_status.rs_id\r\n" + 
				"  full outer join ers_reimbursement_type on ers_reimbursments.rt_type = ers_reimbursement_type.rt_id \r\n" + 
				"   where ers_reimbursment_status.rs_status = 'Pending'";
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information

		while (rs.next()) {
			AdminView view = new AdminView();
			view.setR_amount(rs.getDouble(2));
			view.setR_description(rs.getString(3));
			view.setR_id(rs.getInt(1));
			view.setTime_submitted(rs.getTimestamp(4).toString());
		
			if(rs.getTimestamp(5) == null)
			{
				view.setTime_resolved("");
			}
			else {
				view.setTime_resolved(rs.getTimestamp(5).toString());

			}
			Users user = getUserFirstname(rs.getInt(6));
			String str = user.getU_firstname() + " " + user.getU_lastname();
			view.setAuthor(str);
			if(rs.getInt(7) == 0) {
				view.setResolverName("");
			}
			else {
				user = getUserFirstname(rs.getInt(7));
				str = user.getU_firstname() + " " + user.getU_lastname();
				view.setResolverName(str);
			}
			view.setRt_status(rs.getString(8));
			view.setRt_type(rs.getString(9));
			/////-------------- here comes blob
		
			InputStream binaryStream = rs.getBinaryStream(10);
			try {
				byte[] bytes = IOUtils.toByteArray(binaryStream);
				String encoded = Base64.getEncoder().encodeToString(bytes);
				String stri = "<img width=\"150\" height=\"150\" src= data:image/png;base64," + encoded + " >";  //converted everything to image
				view.setImage(stri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			list.add(view);
		}
		ps.close();
		con.close();
		
		return list;
	}

	public ArrayList<AdminView> getApprovedRequest() throws SQLException {
		ArrayList<AdminView> list = new ArrayList();
//		
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection();
		String sql = " select ers_reimbursments.r_id,ers_reimbursments.r_amount,ers_reimbursments.r_description, ers_reimbursments.r_submitted,\r\n" + 
				"    ers_reimbursments.r_resolved,ers_reimbursments.U_id_author,ers_reimbursments.U_ID_RESOLVER , ers_reimbursment_status.rs_status,ers_reimbursement_type.rt_type,\r\n" + 
				" ers_reimbursments.R_RECEIPT\r\n" + 
				"    from ers_reimbursments \r\n" + 
				"  full outer join ers_reimbursment_status on ers_reimbursments.rt_status = ers_reimbursment_status.rs_id\r\n" + 
				"  full outer join ers_reimbursement_type on ers_reimbursments.rt_type = ers_reimbursement_type.rt_id \r\n" + 
				"   where ers_reimbursment_status.rs_status = 'Approved'";
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information

		while (rs.next()) {
			AdminView view = new AdminView();
			view.setR_amount(rs.getDouble(2));
			view.setR_description(rs.getString(3));
			view.setR_id(rs.getInt(1));
			view.setTime_submitted(rs.getTimestamp(4).toString());
		
			if(rs.getTimestamp(5) == null)
			{
				view.setTime_resolved("");
			}
			else {
				view.setTime_resolved(rs.getTimestamp(5).toString());

			}
			Users user = getUserFirstname(rs.getInt(6));
			String str = user.getU_firstname() + " " + user.getU_lastname();
			view.setAuthor(str);
			if(rs.getInt(7) == 0) {
				view.setResolverName("");
			}
			else {
				user = getUserFirstname(rs.getInt(7));
				str = user.getU_firstname() + " " + user.getU_lastname();
				view.setResolverName(str);
			}
			view.setRt_status(rs.getString(8));
			view.setRt_type(rs.getString(9));
			/////-------------- here comes blob
		
			InputStream binaryStream = rs.getBinaryStream(10);
			try {
				byte[] bytes = IOUtils.toByteArray(binaryStream);
				String encoded = Base64.getEncoder().encodeToString(bytes);
				String stri = "<img width=\"150\" height=\"150\" src= data:image/png;base64," + encoded + " >"; //converted everything to image
				view.setImage(stri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			list.add(view);
		}
		ps.close();
		con.close();
		
		return list;
	
	}
	public ArrayList<AdminView> getOneRequest(int id) throws SQLException{
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection();
		ArrayList<AdminView> list = new ArrayList();
		String sql = "select ers_reimbursments.r_id,ers_reimbursments.r_amount,ers_reimbursments.r_description, ers_reimbursments.r_submitted, \r\n" + 
				"       ers_reimbursments.r_resolved,ers_reimbursments.U_id_author,ers_reimbursments.U_ID_RESOLVER , ers_reimbursment_status.rs_status,ers_reimbursement_type.rt_type,  \r\n" + 
				"     ers_reimbursments.R_RECEIPT \r\n" + 
				"   from ers_reimbursments \r\n" + 
				" full outer join ers_reimbursment_status on ers_reimbursments.rt_status = ers_reimbursment_status.rs_id \r\n" + 
				"full outer join ers_reimbursement_type on ers_reimbursments.rt_type = ers_reimbursement_type.rt_id  \r\n" + 
				"     where ers_reimbursments.r_id = ?";
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();//pull information

		while (rs.next()) {
			AdminView view = new AdminView();

			view.setR_amount(rs.getDouble(2));
			view.setR_description(rs.getString(3));
			view.setR_id(rs.getInt(1));
			view.setTime_submitted(rs.getTimestamp(4).toString());
		
			if(rs.getTimestamp(5) == null)
			{
				view.setTime_resolved("");
			}
			else {
				view.setTime_resolved(rs.getTimestamp(5).toString());

			}
			Users user = getUserFirstname(rs.getInt(6));
			String str = user.getU_firstname() + " " + user.getU_lastname();
			view.setAuthor(str);
			if(rs.getInt(7) == 0) {
				view.setResolverName("");
			}
			else {
				user = getUserFirstname(rs.getInt(7));
				str = user.getU_firstname() + " " + user.getU_lastname();
				view.setResolverName(str);
			}
			view.setRt_status(rs.getString(8));
			view.setRt_type(rs.getString(9));
			/////-------------- here comes blob
		
			InputStream binaryStream = rs.getBinaryStream(10);
			try {
				byte[] bytes = IOUtils.toByteArray(binaryStream);
				String encoded = Base64.getEncoder().encodeToString(bytes);
				String stri = "<img width=\"150\" height=\"150\" src= data:image/png;base64," + encoded + ">"; //converted everything to image
				view.setImage(stri);
				list.add(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		ps.close();
		con.close();
		
		return list;
	}
	public ArrayList<AdminView> getOneUserRequest(int id) throws SQLException
	{
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection();
		ArrayList<AdminView> list = new ArrayList();
		String sql = "select ers_reimbursments.r_id,ers_reimbursments.r_amount,ers_reimbursments.r_description, ers_reimbursments.r_submitted, \r\n" + 
				"       ers_reimbursments.r_resolved,ers_reimbursments.U_id_author,ers_reimbursments.U_ID_RESOLVER , ers_reimbursment_status.rs_status,ers_reimbursement_type.rt_type,  \r\n" + 
				"     ers_reimbursments.R_RECEIPT \r\n" + 
				"   from ers_reimbursments \r\n" + 
				" full outer join ers_reimbursment_status on ers_reimbursments.rt_status = ers_reimbursment_status.rs_id \r\n" + 
				"full outer join ers_reimbursement_type on ers_reimbursments.rt_type = ers_reimbursement_type.rt_id  \r\n" + 
				"     where ers_reimbursments.U_ID_AUTHOR = ?";
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();//pull information

		while (rs.next()) {
			AdminView view = new AdminView();

			view.setR_amount(rs.getDouble(2));
			view.setR_description(rs.getString(3));
			view.setR_id(rs.getInt(1));
			view.setTime_submitted(rs.getTimestamp(4).toString());
		
			if(rs.getTimestamp(5) == null)
			{
				view.setTime_resolved("");
			}
			else {
				view.setTime_resolved(rs.getTimestamp(5).toString());

			}
			Users user = getUserFirstname(rs.getInt(6));
			String str = user.getU_firstname() + " " + user.getU_lastname();
			view.setAuthor(str);
			if(rs.getInt(7) == 0) {
				view.setResolverName("");
			}
			else {
				user = getUserFirstname(rs.getInt(7));
				str = user.getU_firstname() + " " + user.getU_lastname();
				view.setResolverName(str);
			}
			view.setRt_status(rs.getString(8));
			view.setRt_type(rs.getString(9));
			/////-------------- here comes blob
		
			InputStream binaryStream = rs.getBinaryStream(10);
			try {
				byte[] bytes = IOUtils.toByteArray(binaryStream);
				String encoded = Base64.getEncoder().encodeToString(bytes);
				String stri = "<img width=\"150\" height=\"150\" src= data:image/png;base64," + encoded + " >"; //converted everything to image
				view.setImage(stri);
			
				list.add(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		ps.close();
		con.close();
		
		return list;	
	}
	public Users getUserFirstname(int id) throws SQLException {
		UserDaoImple dao = new UserDaoImple();
		ArrayList <Users> user = dao.getAllUsers();
		for(int i = 0; i < user.size(); i++) {
			if(user.get(i).getU_id() == id)
			{
				return user.get(i);
			}
		}
		return null;
	}

	public boolean searchAppId(int id) throws SQLException {
		ArrayList<ErsReimbursement> list = getAllReimbursement();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getR_id() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean searchAuthorId(int id) throws SQLException {
		UserDaoImple dao = new UserDaoImple();
		ArrayList<Users> list = dao.getAllUsers();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getU_id() == id) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<EmployeeView> getEmployee() throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "select ERS_USERS.u_id, ERS_USERS.u_username, ERS_USERS.u_firstname, ERS_USERS.u_lastname, ERS_USERS.u_email from ERS_USERS \r\n" + 
				"    LEFT JOIN  ers_user_roles\r\n" + 
				"    on ERS_USERS.ur_id = ers_user_roles.UR_ID\r\n" + 
				"    where ers_user_roles.ur_role = 'user'";

		ArrayList<EmployeeView> list = new ArrayList();
		
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information
		
		while (rs.next()) {
			logger.info("retrieving object");
			EmployeeView emp = new EmployeeView();
			emp.setEmail(rs.getString(5));
			emp.setFname(rs.getString(3));
			emp.setU_id(rs.getInt(1));
			emp.setUsername(rs.getString(2));
			emp.setLname(rs.getString(4));
			list.add(emp);// save data in arraylist

			
		}
		//close connection
		ps.close();
		rs.close();
		con.close();
		return list;
	}
	
}
