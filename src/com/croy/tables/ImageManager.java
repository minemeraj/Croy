package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.net.aso.n;

import com.croy.beans.Ad;
import com.croy.beans.Image;
import com.croy.db.ConnectionManager;

public class ImageManager {

	public static boolean insert(Image image) throws SQLException {

		String sql = "INSERT INTO DBUSER.IMAGE (AD_ID, URL) values(?, ?)";
		ResultSet keys = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, image.getAd_id());
			stmt.setString(2, image.getUrl());

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

	public static ArrayList<String> getImageUrlsByAdId(int ad_id) throws SQLException {

		ArrayList<String> imageUrls = new ArrayList<String>();

		String sql = "SELECT URL FROM dbuser.IMAGE WHERE AD_ID = ?";

		ResultSet rs = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, ad_id);

			rs = stmt.executeQuery();

			while (rs.next()) {
				imageUrls.add(rs.getString("URL"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return imageUrls;

	}

}
