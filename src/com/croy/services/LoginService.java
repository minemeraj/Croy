package com.croy.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.croy.db.ConnectionManager;

public class LoginService {

	public static boolean authenticate(String email, String password) {
		boolean status = false;
		String sql = "SELECT * FROM DBUSER.USERS WHERE EMAIL = ? AND PASSWORD = ?";
		ResultSet rs = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				status = true;
			} else {
				status = false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return status;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return status;
	}
}
