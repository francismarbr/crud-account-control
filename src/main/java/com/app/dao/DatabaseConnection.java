package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3308/db_manager";
	private String user = "root";
	private String password = "1405";
	
	public Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
}
