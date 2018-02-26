package com.revature;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserResultSetMetaData<T> {

	public void mapToObj(T t, ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			for (int i = 0; i < numCols; i++) {

				rsmd.getColumnLabel(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
