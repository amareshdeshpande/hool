package com.hool.app.model;

public class DealResult {
    int tableId;
	private int pole;
	private String poleName;
	private String bid;
	private int num;
	private String suit;
	private int doubleCount;
	private int[] wonTricks;
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getPole() {
		return pole;
	}
	public void setPole(int pole) {
		this.pole = pole;
	}
	public String getPoleName() {
		return poleName;
	}
	public void setPoleName(String poleName) {
		this.poleName = poleName;
	}
	public String getBID() {
		return bid;
	}
	public void setBID(String bID) {
		bid = bID;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public int getDoubleCount() {
		return doubleCount;
	}
	public void setDoubleCount(int doubleCount) {
		this.doubleCount = doubleCount;
	}
	public int[] getWonTricks() {
		return wonTricks;
	}
	public void setWonTricks(int[] wonTricks) {
		this.wonTricks = wonTricks;
	}
	
	
	
}



