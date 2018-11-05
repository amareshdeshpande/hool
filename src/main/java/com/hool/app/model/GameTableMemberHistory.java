package com.hool.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_table_member_history")
public class GameTableMemberHistory {
	
	@EmbeddedId
    private GameTableMemberHistoryKey gameTableMemberHistoryKey;
	
	@Column(name = "join_time")	
	private Date joinTime;	
	
	@Column(name = "pole")
	private String pole;

	public GameTableMemberHistoryKey getGameTableMemberHistoryKey() {
		return gameTableMemberHistoryKey;
	}

	public void setGameTableMemberHistoryKey(GameTableMemberHistoryKey gameTableMemberHistoryKey) {
		this.gameTableMemberHistoryKey = gameTableMemberHistoryKey;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getPole() {
		return pole;
	}

	public void setPole(String pole) {
		this.pole = pole;
	}
}
