package com.swempire.web.condition.VO;

public class ConditionVO {
	
	int bid;
	int[] bidArray;
	String orga_name;
	String orga_url;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getOrga_url() {
		return orga_url;
	}
	public void setOrga_url(String orga_url) {
		this.orga_url = orga_url;
	}
	public int[] getBidArray() {
		return bidArray;
	}
	public void setBidArray(int[] bidArray) {
		this.bidArray = bidArray;
	}

	
	/*
	 * @Override public String toString() { return "ConditionVO [bid=" + bid +
	 * ", bidArray=" + Arrays.toString(bidArray) + ", orga_name=" + orga_name +
	 * ", orga_url=" + orga_url + "]"; }
	 */

	

}
