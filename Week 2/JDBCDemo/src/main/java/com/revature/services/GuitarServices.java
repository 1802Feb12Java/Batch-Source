package com.revature.services;


import java.util.List;



public class GuitarServices implements GuitarDAO {

	public void addGuitar() {
		// TODO Auto-generated method stub

	}

	public Guitar getGuitar(int id) throws SQLException {
		List<Guitar> gList= new ArrayList<Guitar>();
		Connection conn = cf.getConnection();
		Statement stmt= ((Connection) conn).createStatement();
		ResultSet rs1= stmt.executeQuery("SELECT * FROM GUITAR WHERE GUITARID=" + id);
		Guitar g =new Guitar();
		while(rs1.next()) {
			gList.add(g);
			
		}	
		conn.close();	
		return gList.get(0);
		
		
	}

	

}
