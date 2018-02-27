package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pt1.Customers;
import com.revature.util.ConnFactory;

public class CustomerService implements CustomerDao {
	
	ConnFactory cf = new ConnFactory();

	@Override
	public List<Customers> retrieveAllCus() {
		try (Connection con = cf.getConnection();) {
			
			List<Customers> cusList = new ArrayList<>();
			
			String sql = "select * FROM Customers";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Customers thisCus = new Customers(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
				cusList.add(thisCus);
			}
			return cusList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void depositCus(String username, double balance) {
		double total = 0;
		if ( balance >= 0) {
		try (Connection con = cf.getConnection();) {
			/*String sql = "{call ADDBAL(?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.setDouble(2, balance);
			cs.execute();
			cs.close();*/
			PreparedStatement pps = con.prepareStatement("SELECT * FROM CUSTOMERS WHERE c_username = ?");
			pps.setString(1,username);
			ResultSet rs1 = pps.executeQuery();
			 
            if (rs1.next()) {
                total = balance + rs1.getInt("c_balance");
            }
            
			String sqlUpdate = "UPDATE CUSTOMERS SET c_balance = ? WHERE c_username = ?";
			PreparedStatement ps = con.prepareStatement(sqlUpdate);
			ps.setDouble(1, total);
			ps.setString(2, username);
			ps.executeUpdate();
			ps.close();
			System.out.println(username+" deposited " + balance + " and has a new balance of "+total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		} else {
			System.out.println("Try again. You cant deposit negative values");
		}
		
	}
	 
	
	@Override
	public void withdrawCus(String username, double balance) {
		if ( balance < 0) {
			System.out.println("Try again. You cant withdraw negative values");
		} else {
			try (Connection con = cf.getConnection();) {
				PreparedStatement pps = con.prepareStatement("SELECT * FROM CUSTOMERS WHERE c_username = ?");
				pps.setString(1,username);
				ResultSet rs1 = pps.executeQuery();
				double res= 0;
	            if (rs1.next()) {
	                res = rs1.getDouble(4) - balance;
	            }
	            if (res > 0) {
				String sqlUpdate = "UPDATE CUSTOMERS SET c_balance = ? WHERE c_username = ?";
				PreparedStatement ps = con.prepareStatement(sqlUpdate);
				ps.setDouble(1, res);
				ps.setString(2, username);
				ps.executeUpdate();
				ps.close();
				System.out.println(username+" withdrew "+ balance + " and has a new balance of "+res);
				} else {
					System.out.println("You cannot withdraw more than what you have in the account");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
	public void closeCus(String username) {
	// Delete from Customers table
	try (Connection con = cf.getConnection();) {	
		String getInfo = "Select * from Customers where c_username = ?";
		PreparedStatement ps1 = con.prepareStatement(getInfo);
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();
		
		// find if given username is a customer
		if(rs1.next()) {		
		CallableStatement cs = con.prepareCall("{call REMCUS(?)}");
		cs.setString(1, username);
		cs.execute();
		cs.close();
			
		/*String delInfo = "Delete from Customers where c_username = ?";
		PreparedStatement ps = con.prepareStatement(delInfo);
		ps.setString(1, username);
		ps.execute();*/
		System.out.println(username+" is no longer a customer!");
		} else {
			System.out.println(username+" is not a customer!");
		}
	}catch (SQLException e) {
		e.printStackTrace();
		}
	}
	     
	@Override
	public boolean checkUser(String cname, String cps) {
		Boolean result = false;
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM customers WHERE c_username = ? AND c_password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,cname);
			ps.setString(2,cps);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = true;
				System.out.println(cname + " is logged in");
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void transferCus(String cname, String tran, double tmoney) {
		double bal = 0;		// bank balance of user
		double bal1 = 0;	// bank balance of target
		
		if (tmoney >= 0) {
		try (Connection con = cf.getConnection();) {
			PreparedStatement pps = con.prepareStatement("SELECT * FROM CUSTOMERS WHERE c_username = ?");
			pps.setString(1,cname);
			ResultSet rs1 = pps.executeQuery();
			if (rs1.next()) {
                bal = rs1.getDouble("c_balance") - tmoney;
            }
            
			if (bal < 0) {
				System.out.println(cname+" does not enough money for the transfer in their account.");
			} else {
			String sqlUpdate = "UPDATE CUSTOMERS SET c_balance = ? WHERE c_username = ?";
			PreparedStatement ps = con.prepareStatement(sqlUpdate);
			ps.setDouble(1, bal);
			ps.setString(2, cname);
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement pps2 = con.prepareStatement("SELECT * FROM CUSTOMERS WHERE c_username = ?");
			pps2.setString(1,tran);
			ResultSet rs2 = pps2.executeQuery();
			if (rs2.next()) {
                bal1 = rs2.getInt("c_balance") + tmoney;
            }
            
			String sqlUpdate2 = "UPDATE CUSTOMERS SET c_balance = ? WHERE c_username = ?";
			PreparedStatement ps2 = con.prepareStatement(sqlUpdate2);
			ps2.setDouble(1, bal1);
			ps2.setString(2, tran);
			ps2.executeUpdate();
			ps2.close();
			System.out.println(cname + " transferred $"+tmoney+" to "+tran);
			System.out.println(cname + " has a new balance of "+bal);
			System.out.println(tran + " has a new balance of "+bal1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		else {
			System.out.println("Try again. You cant transfer negative amount");
		}		
	}

	@Override
	public void moveFromApp(String per3) {
		// TODO Auto-generated method stub
		try (Connection con = cf.getConnection();) {
			String getInfo = "Select * from Applicants where a_username = ?";
			PreparedStatement ps1 = con.prepareStatement(getInfo);
			ps1.setString(1, per3);
			ResultSet rs1 = ps1.executeQuery();
			
			// find if given username is an applicant
			if(rs1.next()) {		
			// insert from applicants into customers
			String sqlInsert = "INSERT INTO CUSTOMERS(c_id,c_username,c_password,c_balance) VALUES(CUSSEQ.nextVal,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(sqlInsert);
			ps2.setString(1, rs1.getString(2));
			ps2.setString(2, rs1.getString(3));
			ps2.setDouble(3, rs1.getDouble(4));
			ps2.executeUpdate();
			// Delete from applicants table
			String delInfo = "Delete from Applicants where a_username = ?";
			PreparedStatement ps3 = con.prepareStatement(delInfo);
			ps3.setString(1, per3);
			ps3.execute();
			System.out.println(per3+" is now a customer!");
			} else {
				System.out.println(per3 + " does not exist.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void infoCus(String name) {
		try (Connection con = cf.getConnection();){
		PreparedStatement ps = con.prepareStatement("Select * from Customers where c_username = ?");
		ps.setString(1,name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println("USERID: "+rs.getInt(1)+", Username: "+rs.getString(2)+", Password: "+rs.getString(3)+", Balance: "+rs.getDouble(4));
		} else {
			System.out.println("Customer doesnt exist");
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public double getBal(String name) {
		try (Connection con = cf.getConnection();){
			PreparedStatement ps = con.prepareStatement("Select * from Customers where c_username = ?");
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble(4);
			} else {
				System.out.println("Customer doesnt exist");
			}
			}
		catch (SQLException e) {
				e.printStackTrace();
			}	
		return 0;
	}

	public boolean checktran(String name) {
		Boolean result = false;
		try (Connection con = cf.getConnection();) {
			
			String sql = "select * FROM customers WHERE c_username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = true;
			} 
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
