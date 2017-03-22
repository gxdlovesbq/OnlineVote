package com.sxt.bean;

public class Vote_subject {
	private String vs_titles;
	private long count;
	private long pcount;
	private int vs_id;

	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getPcount() {
		return pcount;
	}
	public void setPcount(long pcount) {
		this.pcount = pcount;
	}
	public String getVs_titles() {
		return vs_titles;
	}
	public void setVs_titles(String vsTitles) {
		vs_titles = vsTitles;
	}
	public int getVs_id() {
		return vs_id;
	}
	public void setVs_id(int vsId) {
		vs_id = vsId;
	}
	
}
