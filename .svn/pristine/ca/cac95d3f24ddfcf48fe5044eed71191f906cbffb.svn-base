package com.hool.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game_table_host_history")
public class GameTableHostHistory {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "table_id")
	private int tableId;
	
	@Column(name = "Host_Id")	
	private int hostId;
	
	@Column(name = "Host_Name")	
	private String hostName;

	@Column(name = "history")
	//@Length(max = 1)
	private int history;
	
	@Column(name = "kibitzer")
	//@Length(max = 1)
	private int kibitzer;
	
	@Column(name = "privacy")
	//@Length(max = 1)
	private int privacy;
	
	@Column(name = "turn_time")	
	private int turnTime;
	
	@Column(name = "start_time")	
	private Date startTime;
	
	@Column(name = "deal_count")	
	private int dealCount;
	
	@Column(name = "No_Of_Player")	
	private int noOfPlayer;
	
	@Column(name = "No_Of_Kibitzer")	
	private int noOfKibitzer;
	
	@Column(name = "ttype")	
	private int tableType;	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}	
	
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public int getKibitzer() {
		return kibitzer;
	}

	public void setKibitzer(int kibitzer) {
		this.kibitzer = kibitzer;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public int getTurnTime() {
		return turnTime;
	}

	public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getDealCount() {
		return dealCount;
	}

	public void setDealCount(int dealCount) {
		this.dealCount = dealCount;
	}

	public int getNoOfPlayer() {
		return noOfPlayer;
	}

	public void setNoOfPlayer(int noOfPlayer) {
		this.noOfPlayer = noOfPlayer<= 0 ? 0 : noOfPlayer;
	}

	public int getNoOfKibitzer() {
		return noOfKibitzer;
	}

	public void setNoOfKibitzer(int noOfKibitzer) {
		this.noOfKibitzer = noOfKibitzer<= 0 ? 0 : noOfKibitzer;
	}

	public int getTableType() {
		return tableType;
	}

	public void setTableType(int tableType) {
		this.tableType = tableType;
	}

}
