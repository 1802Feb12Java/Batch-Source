package com.revature.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TransactionDAO {
	ResultSet readTransactionListForUser(int user_id) throws SQLException;
	void insertTransaction(int user_id, String description) throws SQLException;
}
