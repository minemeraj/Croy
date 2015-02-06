package com.croy.tables;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.croy.beans.Ad;
import com.croy.db.ConnectionManager;

public class AdManager {

	public static ArrayList<Ad> getAllAds() {

		return null;
	}

	public static boolean insert(Ad ad) throws SQLException {

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

	public static int insertAndGetTheId(Ad ad) throws SQLException {

		int ad_id = 0;
		String sql = "BEGIN INSERT INTO DBUSER.AD (USER_ID, CATEGORY_ID, SUB_CATEGORY_ID, TITLE, PRICE, DESCRIPTION, POST_DATE) "
				+ "VALUES (?, ?, ?, ?, ?, ?, SYSDATE) returning AD_ID into ?; END;";
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(sql);) {
			cs.setInt(1, ad.getUser_id());
			cs.setInt(2, ad.getCategory_id());
			cs.setInt(3, ad.getSub_category_id());
			cs.setString(4, ad.getTitle());
			cs.setString(5, ad.getPrice());
			cs.setString(6, ad.getDescription());
			cs.registerOutParameter(7, OracleTypes.NUMBER);
			int affected = cs.executeUpdate();

			if (affected == 1) {
				ad_id = cs.getInt(7);
				System.out.println("One Row Inserted");
			} else {
				System.err.println("No rows affected");
				return 0;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return 0;
		}

		return ad_id;
	}

	public static Ad getAdById(int ad_id) throws SQLException {
		Ad ad = new Ad();
		String sql = "SELECT * FROM dbuser.ad WHERE ad_id = ?";
		ResultSet rs = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, ad_id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				ad.setUser_id(rs.getInt("USER_ID"));
				ad.setCategory_id(rs.getInt("CATEGORY_ID"));
				ad.setSub_category_id(rs.getInt("SUB_CATEGORY_ID"));
				ad.setTitle(rs.getString("TITLE"));
				ad.setPrice(rs.getString("PRICE"));
				ad.setDescription(rs.getString("DESCRIPTION"));
				ad.setPost_date(rs.getDate("POST_DATE"));
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return ad;

	}
}
