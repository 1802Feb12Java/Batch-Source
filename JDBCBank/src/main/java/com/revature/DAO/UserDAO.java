package com.revature.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

//user_id int primary key,
//accesslevel number(1,0) not null check (accesslevel = 1 or accesslevel = 0), --1 will be superuser, 0 will be normal user
//username varchar(100) not null,
//pass varchar(100) not null,
//fullname varchar(100) not null

public interface UserDAO {
	ResultSet readAllUsers() throws SQLException;
	ResultSet readUser(int user_id) throws SQLException;
	int insertUser(int accesslevel, String username, String password, String fullname) throws SQLException;
	void deleteUser(int user_id) throws SQLException;
}
