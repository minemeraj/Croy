package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.croy.beans.Ad;
import com.croy.db.ConnectionManager;

public class AdManager {

	public static ArrayList<Ad> getAllAds() {

		return null;
	}

	public static boolean insertAd(Ad ad) throws SQLException {

		String sql = "INSERT INTO DBUSER.AD (USER_ID, CATEGORY_ID, SUB_CATEGORY_ID, TITLE, PRICE, DESCRIPTION, POST_DATE) "
				+ "VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";
		ResultSet keys = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, ad.getUser_id());
			stmt.setInt(2, ad.getCategory_id());
			stmt.setInt(3, ad.getSub_category_id());
			stmt.setString(4, ad.getTitle());
			stmt.setString(5, ad.getPrice());
			stmt.setString(6, ad.getDescription());
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
}
