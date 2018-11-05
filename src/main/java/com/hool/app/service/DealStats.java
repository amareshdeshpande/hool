package com.hool.app.service;

import com.hool.app.model.HandStats;

public class DealStats {
	private int totalHcp = 0;
	private int totalDisp = 0;
	private HandStats[] handStats = new HandStats[4];
	private Hand[][] hands;
	private int noOfHands;
	private int posOfHands = 0;

	/*public DealStats() {
		for (int i = 0; i < 4; i++) {
			handStats[i] = new HandStats(i);
		}
	}

	public DealStats(Hand[] hands) {
		for (int i = 0; i < 4; i++) {
			handStats[i] = new HandStats(i, hands[i]);
			totalHcp += handStats[i].getTotalHcp();
			totalDisp += handStats[i].getTotalDisp();
		}
	}

	public DealStats(int noOfHands){
	this.noOfHands=noOfHands;
	hands=new Hand[noOfHands][4];
	
	}*/
}