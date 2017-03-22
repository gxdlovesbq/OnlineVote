package com.sxt.bean;
public class Vote_user {
	private String vu_name;
	private String vu_passworld;
	
	public Vote_user() {
		super();
	}
	public Vote_user(String vuName, String vuPassworld) {
		super();
		vu_name = vuName;
		vu_passworld = vuPassworld;
	}

	public String getVu_name() {
		return vu_name;
	}
	public void setVu_name(String vuName) {
		vu_name = vuName;
	}
	public String getVu_passworld() {
		return vu_passworld;
	}
	public void setVu_passworld(String vuPassworld) {
		vu_passworld = vuPassworld;
	}

	
}
