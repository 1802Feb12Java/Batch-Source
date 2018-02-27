package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Transaction;

/**
 * 
 * CRUD Ops
 *
 */
public interface TransactionDao {

	public void addTransaction(Transaction transaction) throws SQLException;

	public Transaction getTransaction(int TransactionId) throws SQLException;

	public void updateTransaction(Transaction transaction) throws SQLException;

	public void deleteTransaction(int transactionId) throws SQLException;

	public List<Transaction> getAllTransactions() throws SQLException;

	public List<Transaction> getAllTransactions(int userId) throws SQLException;
}
