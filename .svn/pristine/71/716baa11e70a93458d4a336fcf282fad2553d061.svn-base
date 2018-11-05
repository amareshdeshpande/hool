package com.hool.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

import com.hool.app.model.Enums.MemberType;

@Entity
@Table(name = "game_table_member")
public class GameTableMember {
	
	@EmbeddedId
    private GameTableMemberKey gameTableMemberKey;
	
	
	@Column(name = "join_time", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")	
	private Date joinTime;	
	
	@Column(name = "pole")
	private String pole;
	
	@Column(name = "member_type")	
	//@Length(max = 10)
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="member_id",  referencedColumnName="member_id", nullable = false, insertable = false, updatable = false)
	private Member member;
	

	
	public GameTableMemberKey getGameTableMemberKey() {
		return gameTableMemberKey;
	}

	public void setGameTableMemberKey(GameTableMemberKey gameTableMemberKey) {
		this.gameTableMemberKey = gameTableMemberKey;
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

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
