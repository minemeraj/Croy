package com.croy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	static Connection conn = null;

	public static Connection getConnection() {
		String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		String USER = "dbuser";
		String PASS = "82664442";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
			System.out.println("Conection Close");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
