package com.hool.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class GameTableMemberHistoryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Column(name = "table_id")
	private int tableId;	
	
	@GeneratedValue
	@Column(name = "table_game_id")
	private String tableGameId;
	
	@GeneratedValue
	@Column(name = "member_id")
	private int memberId;
	
	public GameTableMemberHistoryKey(){};
	public GameTableMemberHistoryKey(int tableId, String tableGameId, int memberId){
		this.tableId=tableId;
		this.tableGameId=tableGameId;
		this.memberId=memberId;
	}
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getTableGameId() {
		return tableGameId;
	}
	public void setTableGameId(String tableGameId) {
		this.tableGameId = tableGameId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
}
