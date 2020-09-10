package com.xlguru.pro1.dbConfig;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn;

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Class path found");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr" ,"hr");
			System.out.println("Connected to Database ");
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("unable to connect");
		}catch(ClassNotFoundException e) {
			System.out.println("Class Not found ");
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnection();
	}
}
