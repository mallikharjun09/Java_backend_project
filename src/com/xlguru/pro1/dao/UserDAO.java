package com.xlguru.pro1.dao;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.User;

public interface UserDAO {
	List<User> userList = new LinkedList<User>();
	Connection conn = DBConnection.getConnection();
	boolean addUser(User user);
	boolean editUser(User user);
	List<User> displayAllUsers();
	List<User> displayAllUsers(String role);
	User displayUser(int id);
	User validateUser(int userId, String password);
	boolean deleteUser(int id);
	boolean editPassword(int userId, String newPassword);
}
