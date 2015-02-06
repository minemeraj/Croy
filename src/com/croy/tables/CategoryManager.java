package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.croy.beans.Category;
import com.croy.beans.SubCategory;
import com.croy.db.ConnectionManager;

public class CategoryManager {

	public static ArrayList<Category> getAllCategory() throws SQLException {
		ArrayList<Category> categories = new ArrayList<Category>();

		String sql = "SELECT * FROM dbuser.category";
		try (Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Category category = new Category();
				category.setCategory_Id(rs.getInt("category_id"));
				category.setCategory(rs.getString("category"));
				categories.add(category);
			}
		}
		return categories;
	}

	public static int getCategory_Id_By_Sub_Category_Id(int sub_category_Id)
			throws SQLException {

		String sql = "SELECT category_id FROM dbuser.sub_category WHERE sub_category_id = ?";
		int category_Id = 0;
		ResultSet rs = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, sub_category_Id);
			rs = stmt.executeQuery();
			rs.next();
			category_Id = rs.getInt("category_id");

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return category_Id;

	}

}
