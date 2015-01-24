package com.croy.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String email;
	private String password;
	private String name;
	private String mobile;
	private int location_id;
	private String location;
	private int area_id;
	private String area;

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAllInfoByStrings(String email, String password, String name,
			String mobile, int location_id, int area_id) {
		this.setEmail(email);
		this.setPassword(password);
		this.setName(name);
		this.setMobile(mobile);
		this.setLocation_id(location_id);
		this.setArea_id(area_id);
	}

	public void setAllInfoByResultSet(ResultSet rs) throws SQLException {

		this.setUser_id(rs.getInt("USER_ID"));
		this.setEmail(rs.getString("EMAIL"));
		this.setName(rs.getString("NAME"));
		this.setMobile(rs.getString("MOBILE"));
		this.setLocation(rs.getString("LOCATION"));
		this.setArea(rs.getString("AREA"));

	}

	@Override
	public String toString() {

		return "USER_ID: " + this.user_id + " EMAIL: " + this.getEmail()
				+ " PASSWORD: " + this.password + " NAME: " + this.getName()
				+ " MOBILE: " + this.getMobile() + " LOCATION_ID: "
				+ this.location_id + " LOCATION: " + this.getLocation()
				+ " AREA_ID: " + this.area_id + "AREA: " + this.area;
	}

}
