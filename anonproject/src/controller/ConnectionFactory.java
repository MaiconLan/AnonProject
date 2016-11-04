package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException{
		
		@SuppressWarnings("unused")
		Connection con;
		
		String url = "jdbc:postgresql://192.168.101.168/chandion";	
		
		DriverManager.registerDriver(new org.postgresql.Driver());
		return DriverManager.getConnection(url, "postgres", "postgres");		
	
	}
}
