package com.croy.beans;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = -6988758077286148610L;

	private int image_id;
	private int ad_id;
	private String url;

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public int getAd_id() {
		return ad_id;
	}

	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
