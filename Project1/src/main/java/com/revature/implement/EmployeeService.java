package com.revature.implement;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;

import com.revature.dao.EmployeeDao;
import com.revature.objects.Employee;
import com.revature.objects.Reimbursement;
import com.revature.util.ConnFactory;

public class EmployeeService implements EmployeeDao{
	ConnFactory cf = new ConnFactory();

	public void newEmp(String us, String psw, String fn, String ln, String email) {
		try (Connection con = cf.getConnection();) {
			String sqlInsert = "INSERT INTO ERS_USERS(U_ID,U_USERNAME,U_PASSWORD,U_FIRSTNAME,U_LASTNAME,U_EMAIL,UR_ID) VALUES(EMPSEQ.nextVal,?,?,?,?,?,1)";
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, us);
			ps.setString(2, psw);
			ps.setString(3, fn);
			ps.setString(4, ln);
			ps.setString(5, email);
			ps.executeUpdate();
			System.out.println(fn + " " + ln + " is now an employee!");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public boolean login(String us, String psw) {
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM ERS_USERS WHERE u_username = ? AND u_password = ? AND ur_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,us);
			ps.setString(2,psw);
			ps.setInt(3, 1);
			
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
	public List<Reimbursement> pendingReq(Integer empId) {
		List<Reimbursement> ReList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {
			String getInfo = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ? and RT_STATUS = ?";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1, empId);
			ps.setInt(2, 1);
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
	public List<Reimbursement> resolvedReq(Integer empId) {
		List<Reimbursement> ReList = new ArrayList<>();
		try (Connection con = cf.getConnection();) {
			String getInfo = "SELECT * FROM ERS_REIMBURSEMENTS WHERE (U_ID_AUTHOR = ? AND RT_STATUS = ?) "
					+ " OR (U_ID_AUTHOR = ? AND RT_STATUS = ?)";
			PreparedStatement ps = con.prepareStatement(getInfo);
			ps.setInt(1, empId);
			ps.setInt(2, 2);
			ps.setInt(3, empId);
			ps.setInt(4, 3);
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
	
	// Maybe later have both personal info and all their reimbursements
//	PreparedStatement ps = con.prepareStatement("Select * from ERS_USERS LEFT JOIN ERS_REIMBURSEMENTS ON "
//			+ " ERS_USERS.U_ID = ERS_REIMBURSEMENTS.U_ID_AUTHOR"
//			+ " WHERE ERS_USERS.U_ID = ?");
//	ps.setInt(1, empId);
//	ResultSet rs = ps.executeQuery();
	
	@Override
	public Employee viewInfo(Integer empId) {
		try (Connection con = cf.getConnection();){
			PreparedStatement ps = con.prepareStatement("Select * from ERS_USERS WHERE ERS_USERS.U_ID = ?");
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
				return emp;
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public void reimburseReq(Double amount, String Description, byte[] receipt, Integer Author, Integer Type) throws IOException {
		try (Connection con = cf.getConnection();) {
			String sqlInsert = "INSERT INTO ERS_REIMBURSEMENTS(R_ID,R_AMOUNT,R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS) "
					+ "VALUES(REQSEQ.nextVal,?,?,?,?,null,?,null,?,1)";
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setDouble(1, amount);
			ps.setString(2, Description);
			ps.setBytes(3,receipt);
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, Author);
			ps.setInt(6, Type);
			ps.executeUpdate();
			con.commit();
            con.close();
			System.out.println(" Request is submitted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	@Override
	public void updateMan(Integer id, String us, String psw, String fn, String ln, String email) {
		try (Connection con = cf.getConnection();) {
			String sqlInsert = "UPDATE ERS_USERS SET U_USERNAME=?,U_PASSWORD=?,U_FIRSTNAME=?,U_LASTNAME=?,U_EMAIL=? WHERE U_ID=?";
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, us);
			ps.setString(2, psw);
			ps.setString(3, fn);
			ps.setString(4, ln);
			ps.setString(5, email);
			ps.setInt(6, id);
			ps.executeUpdate();
			con.commit();
			System.out.println(id+us+psw+fn+ln+email);
			System.out.println("did the update");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
