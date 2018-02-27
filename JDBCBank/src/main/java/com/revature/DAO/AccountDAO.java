package com.revature.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

//account_id int,
//user_id int not null,
//second_user_id int,
//balance number(22,2) not null,
//approved number(1,0) not null check (approved = 1 or approved = 0),    --1 will be approved, 0 will be not approved
//foreign key (user_id) references usertable(user_id),
//foreign key (second_user_id) references usertable(user_id)

public interface AccountDAO {
	ResultSet readAllAccounts() throws SQLException;
	int insertAccount(int user_id, double balance, boolean approved) throws SQLException;
	int insertJointAccount(int user_id, int second_user_id, double balance, boolean approved) throws SQLException;
	ResultSet readAccount(int account_id) throws SQLException;
	void approveAccount(int account_id) throws SQLException;
	void decreaseBalance(int account_id, int amount) throws SQLException;
	void increaseBalance(int account_id, int amount) throws SQLException;
	void transferMoney(int withdrawAccountID, int depositAccountID, int amount) throws SQLException;
	void deleteAccount(int account_id) throws SQLException;
}
