package com.hool.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game_table_point_history")
public class GameTablePointHistory {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "table_id")
	private int tableId;
	
	@Column(name = "round_id")	
	private int roundId;
	
	@Column(name = "playing_side")	
	private String playingSide;

	@Column(name = "points")	
	private int points;
	
	@Column(name = "created_time")	
	private Date createdTime;

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

	public int getRoundId() {
		return roundId;
	}

	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	public String getPlayingSide() {
		return playingSide;
	}

	public void setPlayingSide(String playingSide) {
		this.playingSide = playingSide;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}	
}
