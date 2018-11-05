package com.hool.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bonus_point_master")
public class BonusPointMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trick")
	private String trick;
	
	@Column(name = "point")
	private int point;

	public String getTrick() {
		return trick;
	}

	public void setTrick(String trick) {
		this.trick = trick;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}	
}
