package com.revature.util;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public interface CommonStatements {
	public static final Logger logger = LogManager.getLogger(CommonStatements.class);

	public static final Function<String, String> SelectAll = (String table) -> "SELECT * FROM " + table;
	public static final Function<String, String> DropTable = (String table) -> "DROP TABLE " + table;

	// "UPDATE ? SET firstName=?, lastName=?, joinDate=?, cardId=?, email=?,
	// password=?, phoneNumber=?, accountType=?, location=? WHERE id=?";
	public static final BiFunction<String, String[], String> Update = (String tableName, String[] cols) -> {
		String statement = "UPDATE " + tableName + " SET ";
		for (int i = 1; i < cols.length; i++) {
			statement += cols[i] + " = ? ";
		}
		statement += " WHERE " + cols[0] + " = ?";
		logger.debug("Created update statement: " + statement);
		return statement;
	};
	// "INSERT INTO ? Values( value1, ...)
	public static final BiFunction<String, String[], String> InsertInto = (String tableName, String[] vals) -> {
		String statement = "INSERT INTO " + tableName + " VALUES( ";
		for (String val : vals) {
			statement += val + ", ";
		}
		// remove last ','
		statement = statement.substring(0, statement.length() - 2);
		statement += " )";
		logger.debug("Created insert statement: " + statement);
		return statement;
	};

	public static final BiFunction<String, Integer, String> InsertIntoFill = (String tableName, Integer count) -> {
		String statement = "INSERT INTO " + tableName + " VALUES( ";
		int cnt = 0;
		while (cnt++ < count) {
			statement += " ?,";
		}
		// remove last ','
		statement = statement.substring(0, statement.length() - 1);
		statement += " )";
		logger.debug("Created insert statement: " + statement);
		return statement;
	};

}
