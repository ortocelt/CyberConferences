package net.etfbl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionString {
	protected static String url = "jdbc:mysql://localhost:3306/cyconf";
	protected static String username = "root";
	protected static String password = "root";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, username, password);
		
	}

}
