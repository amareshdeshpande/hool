package com.hool.app.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HandStats {
	private Integer noOfCardSInSuit[]={0,0,0,0};
	private int hcp=0;
	private Integer[] pattern={0,0,0,0};
	
	HandStats(){};
	
	public HandStats(List<String> cards){
		this.getNoCardsInSuit(cards);
	}
	
	public Integer[] getNoOfCardSInSuit() {
		return this.noOfCardSInSuit;
	}
	
	public int getHcp() {
		return this.hcp;
	}
	
	public Integer[] getPattern() {		
		pattern=this.noOfCardSInSuit;
		Arrays.sort(pattern,Collections.reverseOrder());		
		return pattern;
	}

	public void getNoCardsInSuit(List<String> cards) {		
		
		for(String card:cards){
			if(card.charAt(1)=='S'){
				this.noOfCardSInSuit[0]+=1;
				this.pattern[0]+=1;	
			}
			
			if(card.charAt(1)=='H'){
				this.noOfCardSInSuit[1]+=1;
				this.pattern[1]+=1;
							
			}
			if(card.charAt(1)=='D'){
				this.noOfCardSInSuit[2]+=1;
				this.pattern[2]+=1;
				
			}
			if(card.charAt(1)=='C'){
				this.noOfCardSInSuit[3]+=1;
				this.pattern[3]+=1;
				
			}
			
			int point=(card.charAt(0)=='A'?4:(card.charAt(0)=='K'?3:(card.charAt(0)=='Q'?2:card.charAt(0)=='J'?1:0)));
			this.hcp+=point;			
		}	
				
		Arrays.sort(this.pattern,Collections.reverseOrder());	
		
	}	
	
	
}
