package net.croy.test;

import java.sql.SQLException;

import com.croy.beans.Ad;
import com.croy.tables.AdManager;

public class Main {

	public static void main(String[] args) {
		try {
			Ad ad = new Ad();
			ad.setUser_id(1);
			ad.setCategory_id(1);
			ad.setSub_category_id(1);
			ad.setTitle("Facebook Likes");
			ad.setPrice("200");
			ad.setDescription("aaakf nakf agabgjabdjg ajkgbadbgadg adgag");
			if (AdManager.insertAd(ad)) {
				System.out.println("ad hoice");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
