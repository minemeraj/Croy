package com.croy.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.croy.beans.Category;
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

}
