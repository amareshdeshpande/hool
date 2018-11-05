package com.hool.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.hool.app.model.ChatMessage;
import com.hool.app.model.GameTableMember;
import com.hool.app.model.Enums.MessageType;
import com.hool.app.service.GameService;
import com.hool.app.service.MemberService;

/**
 * Created by Upen Singh on 16th April 2018.
 */
@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    
    @Autowired
	private GameService gameService;
    
    @Autowired
   	private MemberService memberService;
    
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    	//String memberId = (String) headerAccessor.getSessionAttributes().get("username");
        logger.info("Received a new web socket connection");      
        //messagingTemplate.convertAndSend("/topic/public", new ChatMessage(memberId,null,MessageType.ONLINE));
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
       
        String memberId = (String) headerAccessor.getSessionAttributes().get("username");        
        //String tableId = (String) headerAccessor.getSessionAttributes().get("tableId");
      
        if(memberId != null && !memberId.isEmpty()) { 
        	String loginId=memberService.findUserNameByID(memberId);
        	GameTableMember m = gameService.removeMemberFromGameTable(Integer.parseInt(memberId));         	
            if(m != null){ 
            	if(gameService.isMemberTableHost(m.getGameTableMemberKey().getTableId(),Integer.parseInt(memberId))){
                	gameService.changeTableHost(m.getGameTableMemberKey().getTableId());
                }
            	messagingTemplate.convertAndSend("/topic/table/" + m.getGameTableMemberKey().getTableId(), new ChatMessage(memberId,loginId,MessageType.LEAVE));
            }                   
            messagingTemplate.convertAndSend("/topic/public", new ChatMessage(memberId, loginId, MessageType.OFFLINE));
            memberService.updateOnlineStatus(0,loginId);
        }        
        //logger.info("User Disconnected :" + memberId);        
    }
}

