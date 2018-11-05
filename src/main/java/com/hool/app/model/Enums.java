package com.hool.app.model;

public class Enums {
	
	public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        SHARE,
        BID,
        UGTL, //Update Game table List
        MESSAGE,
        REQUEST,
        ONLINE,
        OFFLINE,
        DISTRIBUTE,
        PLAY_CARD,
        START_PLAY,
        SCORE
    }
	
	public enum MemberType {
        PLAYER,
        HOST,
        KIBITZER
    }
	
	public enum ResponseStatus {	
	    SUCCESS, 
	    ERROR, 
	    INFO;	
	}	
}
