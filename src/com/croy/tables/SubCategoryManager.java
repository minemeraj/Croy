package com.croy.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.croy.beans.SubCategory;
import com.croy.db.ConnectionManager;

public class SubCategoryManager {

	public static ArrayList<SubCategory> getSubCategoryByCategoryId(
			int category_Id) throws SQLException {

		ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();

		String sql = "SELECT * FROM dbuser.sub_category WHERE category_id = ?";
		ResultSet rs = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, category_Id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				SubCategory subCategory = new SubCategory();
				subCategory.setSub_Category_id(rs.getInt("sub_category_id"));
				subCategory.setCategory_id(rs.getInt("category_id"));
				subCategory.setSub_Category(rs.getString("sub_category"));
				subCategories.add(subCategory);
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		return subCategories;

	}

}
