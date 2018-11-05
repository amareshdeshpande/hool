package com.hool.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class GameTableMemberKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Column(name = "table_id")
	private int tableId;	
	@GeneratedValue
	@Column(name = "member_id")
	private int memberId;
	public GameTableMemberKey(){};
	public GameTableMemberKey(int tableId, int memberId){
		this.tableId=tableId;
		this.memberId=memberId;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name="member_id", referencedColumnName="member_id")
	/*private Member member;

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}*/
	
}
