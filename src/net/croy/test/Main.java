package net.croy.test;

import java.sql.SQLException;
import java.util.ArrayList;

import oracle.net.aso.n;

import com.croy.beans.Ad;
import com.croy.beans.Category;
import com.croy.beans.Image;
import com.croy.tables.AdManager;
import com.croy.tables.CategoryManager;
import com.croy.tables.ImageManager;

public class Main {

	public static void main(String[] args) {
		try {
			// Ad ad = new Ad();
			// ad.setUser_id(1);
			// ad.setCategory_id(1);
			// ad.setSub_category_id(1);
			// ad.setTitle("Facebook Likes");
			// ad.setPrice("200");
			// ad.setDescription("aaakf nakf agabgjabdjg ajkgbadbgadg adgag");
			// if (AdManager.insert(ad)) {
			// System.out.println("ad hoice");
			// }

			Image image = new Image();
			image.setAd_id(11);
			image.setUrl("/images/img.png");
			if (ImageManager.insert(image)) {
				System.out.println("Image Inserted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ArrayList<Category> categories = CategoryManager.getAllCategory();

			for (Category category : categories) {

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
