package databaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bms";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
