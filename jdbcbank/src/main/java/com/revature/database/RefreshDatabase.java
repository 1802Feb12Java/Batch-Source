package com.revature.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.util.ConnectionFactory;

/**
 * 
 * refreshes the entire bank account database by dropping every table and
 * creating
 *
 */
public class RefreshDatabase {
	private static final Logger logger = LogManager.getLogger(RefreshDatabase.class);

	static Function<String, String> dropTable = (String tableName) -> {
		return "DROP TABLE " + tableName;
	};

	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		PreparedStatement prepStatement;
		ResultSet rs;
		try {
			// drop all the tables first
			if (true) {
				try {
					prepStatement = connection.prepareStatement(dropTable.apply(CreateBank.TRANSACTION_TABLE_NAME));
					rs = prepStatement.executeQuery();
				} catch (SQLException e) {
				}
				try {
					prepStatement = connection
							.prepareStatement(dropTable.apply(CreateBank.USER_BANK_ACCOUNT_TABLE_NAME));
					rs = prepStatement.executeQuery();
				} catch (SQLException e) {
				}
				try {
					prepStatement = connection.prepareStatement(dropTable.apply(CreateBank.BANK_ACCOUNT_TABLE_NAME));
					rs = prepStatement.executeQuery();
				} catch (SQLException e) {
				}
				try {
					prepStatement = connection.prepareStatement(dropTable.apply(CreateBank.USER_TABLE_NAME));
					rs = prepStatement.executeQuery();
				} catch (SQLException e) {
				}

			}
			prepStatement = connection.prepareStatement(CreateBank.CREATE_USER_TABLE);
			rs = prepStatement.executeQuery();
			logger.info("CREATE_USER_TABLE TABLE CREATED");

			prepStatement = connection.prepareStatement(CreateBank.CREATE_BANK_ACCOUNT_TABLE);
			rs = prepStatement.executeQuery();
			logger.info("CREATE_BANK_ACCOUNT_TABLE TABLE CREATED");
			prepStatement = connection.prepareStatement(CreateBank.CREATE_TRANSACTION_TABLE);
			rs = prepStatement.executeQuery();
			logger.info("CREATE_TRANSACTION_TABLE TABLE CREATED");
			prepStatement = connection.prepareStatement(CreateBank.CREATE_USER_BANK_ACCOUNT_TABLE);
			rs = prepStatement.executeQuery();
			logger.info("CREATE_USER_BANK_ACCOUNT_TABLE TABLE CREATED");

		} catch (SQLException e1) {
			logger.error("SQL ERROR");
			logger.error(e1.getSQLState());
			logger.error(e1.getMessage());
			logger.error(e1.getErrorCode());
			e1.printStackTrace();
		}

	}
}
