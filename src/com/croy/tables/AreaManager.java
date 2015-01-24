package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.croy.beans.Area;
import com.croy.db.ConnectionManager;

public class AreaManager {

	public static ArrayList<Area> getAreaByLocationId(int location_id)
			throws SQLException {

		ArrayList<Area> areas = new ArrayList<Area>();

		String sql = "SELECT * FROM dbuser.area WHERE location_id = ?";
		ResultSet rs = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, location_id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Area area = new Area();
				area.setArea_id(rs.getInt("area_id"));
				area.setLocation_id(rs.getInt("location_id"));
				area.setArea(rs.getString("area"));
				areas.add(area);
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return areas;

	}

	public static int getLocationIdByAreaId(int area_id) throws SQLException {
		String sql = "SELECT LOCATION_ID FROM dbuser.area WHERE area_id = ?";
		ResultSet rs = null;
		int location_id = 0;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, area_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				location_id = rs.getInt("location_id");
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return location_id;

	}

	public static String getAreaById(int area_id) throws SQLException {
		String area = null;
		String sql = "SELECT LOCATION_ID FROM dbuser.area WHERE area_id = ?";
		ResultSet rs = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, area_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				area = rs.getString("area");
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return area;

	}
}
