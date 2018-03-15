package com.revature.implement;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import java.util.Base64.Encoder;

import com.revature.dao.ManagerDao;
import com.revature.objects.Employee;
import com.revature.objects.Reimbursement;
import com.revature.util.ConnFactory;

public class ManagerService implements ManagerDao{
	ConnFactory cf = new ConnFactory();

	@Override
	public void newMan(String us, String psw, String fn, String ln, String email) {
		try (Connection con = cf.getConnection();) {
			String sqlInsert = "INSERT INTO ERS_USERS(U_ID,U_USERNAME,U_PASSWORD,U_FIRSTNAME,U_LASTNAME,U_EMAIL,UR_ID) VALUES(MANSEQ.nextVal,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, us);
			ps.setString(2, psw);
			ps.setString(3, fn);
			ps.setString(4, ln);
			ps.setString(5, email);
			ps.setInt(6, 2);
			ps.executeUpdate();
			System.out.println(fn + " " + ln + " is now a manager!");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public Integer getId(String Username) {
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM ERS_USERS WHERE u_username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,Username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//shouldnt get here
		return null;
	}
	
	@Override
	public String getType(Integer type) {
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM ERS_REIMBURSEMENT_TYPE WHERE RT_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,type);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//shouldnt get here
		return null;
	}
	
	@Override
	public String getStatus(Integer Status) {
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM ERS_REIMBURSEMENT_STATUS WHERE RS_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Status);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//shouldnt get here
		return null;
	}
	
	@Override
	public String getRole (Integer Id) {
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM ERS_USER_ROLES WHERE UR_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//shouldnt get here
		return null;
	}

	@Override
	public boolean login(String us, String psw) {
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM ERS_USERS WHERE u_username = ? AND u_password = ? AND ur_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,us);
			ps.setString(2,psw);
			ps.setInt(3, 2);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public List<Reimbursement> pendingReq() {
		List<Reimbursement> ReList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {
			String getInfo = "SELECT * FROM ERS_USERS RIGHT JOIN ERS_REIMBURSEMENTS ON ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_ID_AUTHOR "
					+ "WHERE ERS_USERS.UR_ID = ? AND ERS_REIMBURSEMENTS.RT_STATUS = ?";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1,1);
			ps.setInt(2,1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ManagerService Mdao = new ManagerService();
				String resolver = Mdao.getName(rs.getInt(15));
				String type = Mdao.getType(rs.getInt(16));
				String status = Mdao.getStatus(rs.getInt(17));
				byte[] receipt = rs.getBytes(11);
				String uglyReceipt = Base64.encodeBase64String(receipt);
				Reimbursement thisReimb = new Reimbursement(rs.getInt(8),rs.getDouble(9),rs.getString(10),uglyReceipt,
											rs.getDate(12),rs.getDate(13),rs.getString(2),resolver,type,status);
				ReList.add(thisReimb);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReList;
	}
//	@Override
//	public List<Reimbursement> pendingReq() {
//		List<Reimbursement> ReList = new ArrayList<>();
//		try (Connection con = cf.getConnection();) {
//			String getInfo = "SELECT * FROM ERS_USERS RIGHT JOIN ERS_REIMBURSEMENTS ON ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_ID_AUTHOR "
//					+ "WHERE ERS_USERS.UR_ID = ? AND ERS_REIMBURSEMENTS.RT_STATUS = ?";
//			PreparedStatement ps = con.prepareStatement(getInfo);
//			ps.setInt(1,1);
//			ps.setInt(2,1);
//			ResultSet rs = ps.executeQuery();
//			String resolver="";
//			String type ="";
//			String status ="";
//			while (rs.next()) {
//				String sql1 = "select * FROM ERS_USERS WHERE U_ID = ?";
//				PreparedStatement ps1 = con.prepareStatement(sql1);
//				ps1.setInt(1,rs.getInt(15));
//				ResultSet rs1 = ps.executeQuery();
//				if(rs1.next()) {
//					resolver = rs1.getString(2);
//				} 
//				String sql2 = "select * FROM ERS_USER_ROLES WHERE UR_ID = ?";
//				PreparedStatement ps2 = con.prepareStatement(sql2);
//				ps2.setInt(1,rs.getInt(16));
//				ResultSet rs2 = ps.executeQuery();
//				if(rs2.next()) {
//					 type = rs2.getString(2);
//				}
//				String sql3 = "select * FROM ERS_REIMBURSEMENT_STATUS WHERE RS_ID = ?";
//				PreparedStatement ps3 = con.prepareStatement(sql3);
//				ps3.setInt(1,rs.getInt(17));
//				ResultSet rs3 = ps.executeQuery();
//				if(rs3.next()) {
//					status = rs3.getString(2);
//				} 
//				byte[] receipt = rs.getBytes(11);
//				String uglyReceipt = Base64.encodeBase64String(receipt);
//				Reimbursement thisReimb = new Reimbursement(rs.getInt(8),rs.getDouble(9),rs.getString(10),uglyReceipt,
//											rs.getDate(12),rs.getDate(13),rs.getString(2),resolver,type,status);
//				ReList.add(thisReimb);
//			}		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ReList;
//	}
	
	
	@Override
	public List<Reimbursement> allReq() {
		List<Reimbursement> ReList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {
			String getInfo = "SELECT * FROM ERS_USERS RIGHT JOIN ERS_REIMBURSEMENTS ON ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_ID_AUTHOR "
					+ "WHERE ERS_USERS.UR_ID = ?";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1,1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ManagerService Mdao = new ManagerService();
				String resolver = Mdao.getName(rs.getInt(15));
				String type = Mdao.getType(rs.getInt(16));
				String status = Mdao.getStatus(rs.getInt(17));
				byte[] receipt = rs.getBytes(11);
				String uglyReceipt = Base64.encodeBase64String(receipt);
				Reimbursement thisReimb = new Reimbursement(rs.getInt(8),rs.getDouble(9),rs.getString(10),uglyReceipt,
											rs.getDate(12),rs.getDate(13),rs.getString(2),resolver,type,status);
				ReList.add(thisReimb);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReList;
	}
	
	@Override
	public List<Reimbursement> resolvedReq() {
		List<Reimbursement> ReList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {		
			String getInfo = "SELECT * FROM ERS_USERS RIGHT JOIN ERS_REIMBURSEMENTS ON ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_ID_AUTHOR "
					+ "WHERE (ERS_USERS.UR_ID = ? AND ERS_REIMBURSEMENTS.RT_STATUS = ?) OR (ERS_USERS.UR_ID = ? AND ERS_REIMBURSEMENTS.RT_STATUS = ?) ";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1,1);
			ps.setInt(2,2);
			ps.setInt(3,1);
			ps.setInt(4,3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ManagerService Mdao = new ManagerService();
				String resolver = Mdao.getName(rs.getInt(15));
				String type = Mdao.getType(rs.getInt(16));
				String status = Mdao.getStatus(rs.getInt(17));
				byte[] receipt = rs.getBytes(11);
				String uglyReceipt = Base64.encodeBase64String(receipt);				
				Reimbursement thisReimb = new Reimbursement(rs.getInt(8),rs.getDouble(9),rs.getString(10),uglyReceipt,
											rs.getDate(12),rs.getDate(13),rs.getString(2),resolver,type,status);
				ReList.add(thisReimb);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReList;
	}

	@Override
	public List<Employee> viewEmp() {
		List<Employee> EmpList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {
			String getInfo = "SELECT * FROM ERS_USERS WHERE UR_ID = ?";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1,1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee thisEmp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				EmpList.add(thisEmp);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return EmpList;
	}

	
	@Override
	public List<Reimbursement> empReq(Integer empId) {
		List<Reimbursement> ReList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {
			String getInfo = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ?";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ManagerService Mdao = new ManagerService();
				String author = Mdao.getName(rs.getInt(7));
				String resolver = Mdao.getName(rs.getInt(8));
				String type = Mdao.getType(rs.getInt(9));
				String status = Mdao.getStatus(rs.getInt(10));
				byte[] receipt = rs.getBytes(4);
				String uglyReceipt = Base64.encodeBase64String(receipt);
				Reimbursement thisRem = new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getString(3),uglyReceipt,
						rs.getDate(5),rs.getDate(6),author,resolver,type,status);
				ReList.add(thisRem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReList;
	}

	@Override
	public void resolve(Integer type, Integer ManagerId, Integer ReimbursementId) {
		try (Connection con = cf.getConnection();) {
			String getInfo = "UPDATE ERS_REIMBURSEMENTS SET RT_STATUS = ?, R_RESOLVED = ?, U_ID_RESOLVER = ? WHERE R_ID = ?";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1, type);
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, ManagerId);
			ps.setInt(4, ReimbursementId);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName(Integer Id) {
		try (Connection con = cf.getConnection();) {
			String sql = "select * FROM ERS_USERS WHERE U_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//shouldnt get here
		return null;
	}
}
