package com.hool.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.hool.app.model.ChatMessage;
import com.hool.app.model.DealHandInfo;
import com.hool.app.model.Enums.MessageType;
import com.hool.app.service.GameService;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private GameService gameService;
	 
	@MessageMapping("/chat.addUser")   
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        //headerAccessor.getSessionAttributes().put("tableId",tableId);        
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {    	
    	return chatMessage;
    }
    
   /*
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {      
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("tableId",null);
        return chatMessage;
    }*/
	
    @MessageMapping("/chat.sendMessage/{tableId}")    
    public void sendMessage1(@DestinationVariable String tableId,  @Payload ChatMessage chatMessage) {    
    	messagingTemplate.convertAndSend("/topic/table/" + tableId, chatMessage);
    }
    
    @MessageMapping("/chat.generateHand/{tableId}")    
    public void generateHand(@DestinationVariable String tableId,  @Payload ChatMessage chatMessage) { 
    	DealHandInfo info=new  DealHandInfo(gameService.getGameTableById(Integer.parseInt(tableId)), gameService.generateHand(Integer.parseInt(tableId)));
		String json = new Gson().toJson(info);
    	chatMessage.setSender("SYSTEM");
    	chatMessage.setType(MessageType.DISTRIBUTE);    	
    	chatMessage.setContent(json);    	
    	messagingTemplate.convertAndSend("/topic/table/" + tableId, chatMessage);
    }
}

