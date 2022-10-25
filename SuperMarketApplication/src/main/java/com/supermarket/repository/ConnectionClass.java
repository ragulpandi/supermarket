package com.supermarket.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "Pitchai@123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
