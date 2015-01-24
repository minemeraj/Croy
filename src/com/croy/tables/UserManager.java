package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.croy.beans.User;
import com.croy.db.ConnectionManager;

public class UserManager {

	public static User get_User_by_id(int user_id) throws SQLException {

		String sql = "SELECT USER_ID, EMAIL, NAME, MOBILE, LOCATION, AREA "
				+ "FROM DBUSER.USERS NATURAL JOIN DBUSER.LOCATION NATURAL JOIN DBUSER.AREA WHERE USER_ID = ?";
		ResultSet rs = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User bean = new User();
				bean.setAllInfoByResultSet(rs);
				return bean;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}

	public static User getUserByEmail(String email) throws SQLException {

		String sql = "SELECT USER_ID, EMAIL, NAME, MOBILE, LOCATION, AREA "
				+ "FROM DBUSER.USERS NATURAL JOIN DBUSER.LOCATION NATURAL JOIN DBUSER.AREA WHERE EMAIL = ?";
		ResultSet rs = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, email);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User bean = new User();
				bean.setAllInfoByResultSet(rs);
				return bean;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}

	public static boolean insert(User bean) throws Exception {

		if (isEmailExists(bean.getEmail())) {
			System.out.println("Email Already Exists");
			return false;
		}

		String sql = "INSERT into DBUSER.USERS (email, password, name, mobile, location_id, area_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, bean.getEmail());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getName());
			stmt.setString(4, bean.getMobile());
			stmt.setInt(5, bean.getLocation_id());
			stmt.setInt(6, bean.getArea_id());
			int affected = stmt.executeUpdate();

			if (affected == 1) {
				System.out.println("One Row Inserted");
			} else {
				System.err.println("No rows affected");
				return false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (keys != null)
				keys.close();
		}
		return true;
	}

	public static boolean isEmailExists(String email) throws SQLException {
		String sql = "SELECT USER_ID FROM DBUSER.USERS WHERE EMAIL = ?";
		ResultSet rs = null;
		boolean status = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (rs.next()) {
				status = true;
			} else {
				status = false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return status;

	}

	public static boolean update(User bean) throws SQLException {
		String sql = "update DBUSER.USERS set "
				+ "EMAIL= ?, PASSWORD= ?, NAME= ?, MOBILE= ?, LOCATION_ID= ?, AREA_ID= ? "
				+ "WHERE USER_ID= ?";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, bean.getEmail());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getName());
			stmt.setString(4, bean.getMobile());
			stmt.setInt(5, bean.getLocation_id());
			stmt.setInt(6, bean.getArea_id());
			stmt.setInt(7, bean.getUser_id());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				System.out.println("User Updated");
				return true;
			} else {
				System.err.println("No rows affected");
				return false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public static boolean delete(int user_id) {
		String sql = "DELETE FROM DBUSER.USERS WHERE USER_ID = ?";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, user_id);

			int affected = stmt.executeUpdate();

			if (affected == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

}
