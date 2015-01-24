package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.croy.beans.Location;
import com.croy.db.ConnectionManager;

public class LocationManager {

	public static ArrayList<Location> getAllLocation() throws SQLException {
		ArrayList<Location> locations = new ArrayList<Location>();

		String sql = "SELECT * FROM dbuser.location";
		try (Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Location location = new Location();
				location.setLocation_id(rs.getInt("location_id"));
				location.setLocation(rs.getString("location"));
				locations.add(location);
			}
		}
		return locations;
	}

	public static String getLocationById(int area_id) throws SQLException {
		String location = null;
		String sql = "SELECT LOCATION_ID FROM dbuser.location WHERE location_id = ?";
		ResultSet rs = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, area_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				location = rs.getString("area");
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return location;

	}
}
