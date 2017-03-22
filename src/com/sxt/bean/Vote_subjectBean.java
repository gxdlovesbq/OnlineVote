package com.sxt.bean;

public class Vote_subjectBean {
	private int vs_id;
	private String vs_titles;
	private int vs_type;
	
	
	public Vote_subjectBean() {
		super();
	}
	public Vote_subjectBean(int vsId, String vsTitles, int vsType) {
		super();
		vs_id = vsId;
		vs_titles = vsTitles;
		vs_type = vsType;
	}
	public int getVs_id() {
		return vs_id;
	}
	public void setVs_id(int vsId) {
		vs_id = vsId;
	}
	public String getVs_titles() {
		return vs_titles;
	}
	public void setVs_titles(String vsTitles) {
		vs_titles = vsTitles;
	}
	public int getVs_type() {
		return vs_type;
	}
	public void setVs_type(int vsType) {
		vs_type = vsType;
	}
	
}
