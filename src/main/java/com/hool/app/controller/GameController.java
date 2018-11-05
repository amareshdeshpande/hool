package com.hool.app.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hool.app.model.Enums.MemberType;
import com.hool.app.model.Enums.MessageType;
import com.hool.app.model.Enums.ResponseStatus;
import com.hool.app.model.ChatMessage;
import com.hool.app.model.DealResult;
import com.hool.app.model.GameTable;
import com.hool.app.model.GameTableMember;
import com.hool.app.model.GameTableMemberKey;
import com.hool.app.model.Member;
import com.hool.app.service.Deck;
import com.hool.app.service.GameService;
import com.hool.app.service.Hand;
import com.hool.app.service.MemberService;

/**
 * @author HCSDELHI2
 *
 */
/**
 * @author HCSDELHI2
 *
 */
@RestController
@RequestMapping("/api/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/table/list", method = RequestMethod.GET)    
	public ResponseEntity<JSONObject> getTables(){
		JSONObject resp=new JSONObject();
        List<GameTable> tables= gameService.getGameTables(); 
        resp.put("status",ResponseStatus.SUCCESS);
		resp.put("list", tables);
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/table/{tableId}", method = RequestMethod.GET)    
	public ResponseEntity<JSONObject> getGameTable(@PathVariable(value = "tableId") int tableId){
		JSONObject resp=new JSONObject();
        GameTable members= gameService.getGameTableById(tableId); 
        if(members!=null){
        	resp.put("status",ResponseStatus.SUCCESS);
        	resp.put("table", members);       
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/table/players/{tableId}", method = RequestMethod.GET)    
	public ResponseEntity<JSONObject> getGameTableMembers(@PathVariable(value = "tableId") int tableId){
		JSONObject resp=new JSONObject();
		List<GameTableMember>  gameTableMember=gameService.getGameTableMembers(tableId);     
        if(gameTableMember!=null){
        	resp.put("status",ResponseStatus.SUCCESS);
        	resp.put("table", gameTableMember);    		
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/table/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> createGameTable(@RequestBody GameTable game){ 		
    	JSONObject resp=new JSONObject();   
    	Object obj= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj instanceof User) {
		
			User user=(User)obj;
			int loggedInMemberId=memberService.findIDByUserName(user.getUsername());
		
			game.setHostId(loggedInMemberId);
			game.setHostName(user.getUsername());
			
			GameTable gameTable=gameService.createGameTable(game , loggedInMemberId); 
			if(gameTable!=null){
				resp.put("status",ResponseStatus.SUCCESS);
				resp.put("gameTable", gameTable);
				resp.put("message", "Game table created successfully!");  
			}else {				
				resp.put("status",ResponseStatus.ERROR);
				resp.put("message", "Table cannot be created");  
			}			
		}
    	return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);    	
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/table/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateGameTable(@RequestBody GameTable game){    
    	JSONObject resp=new JSONObject();    	  		
		
		Object obj= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj instanceof User) {
			User user=(User)obj;
			//GameTable updated = findTodoEntryById(game.getId());
			
			GameTable gameTable=  gameService.updateGameTable(game); 
			
			if(gameTable!=null){
				resp.put("status",ResponseStatus.SUCCESS);
				resp.put("gameTable", gameTable);
				resp.put("message", "Game table created successfully!");  
			}else {				
				resp.put("status",ResponseStatus.ERROR);
				resp.put("message", "Table cannot be created");  
			}
			
		}
    	return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);    	
    } 
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/table/change/member/type", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> changeMemberTypeInGameTable(@RequestBody GameTableMember gameMember){    
    	JSONObject resp=new JSONObject();    	
		GameTableMember gameTableMember=  gameService.changeMemberTypeInGameTable(gameMember);		
		if(gameTableMember!=null){			
			resp.put("status",ResponseStatus.SUCCESS);
			resp.put("gameTable", gameTableMember);
			resp.put("message", "Game table member updated successfully!"); 			
		}else {				
			resp.put("status",ResponseStatus.ERROR);
			resp.put("message", "Table cannot be created");  
		}
    	return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);    	
    } 
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/table/remove/{tableId}", method = RequestMethod.DELETE)    
	public ResponseEntity<JSONObject> removeGameTable(@PathVariable(value = "tableId") int tableId){
		JSONObject resp=new JSONObject();
        GameTable members= gameService.getGameTableById(tableId); 
        if(members!=null){
        	resp.put("status",ResponseStatus.SUCCESS);
        	resp.put("table", members);       
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="table/join", method = RequestMethod.POST)  
	public ResponseEntity<JSONObject> JoinGameTable(@RequestBody GameTableMember gameTblMember){
		JSONObject resp=new JSONObject();
	    if(gameService.joinGameTable(gameTblMember)){		    	
	    	//this line of code only to update the number of Player as well as
	    	
	    	GameTable gmt=gameService.changeNoOfMemberAtGameTable(gameTblMember.getGameTableMemberKey().getTableId(), gameTblMember.getMemberType(), MessageType.JOIN);		    	
	    	
	    	resp.put("tblInfo",gmt);
	    	resp.put("status",ResponseStatus.SUCCESS);        	     
        }else{
        	resp.put("status",ResponseStatus.ERROR);        	
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/table/leave", method = RequestMethod.POST)  
	public ResponseEntity<JSONObject> leaveGameTable(@RequestBody GameTableMemberKey gameTblMemberKey){
		JSONObject resp=new JSONObject();	   
		gameService.removeMemberFromGameTable(gameTblMemberKey.getTableId(),gameTblMemberKey.getMemberId()); 
		if(gameService.isMemberTableHost(gameTblMemberKey.getTableId(),gameTblMemberKey.getMemberId())){
        	gameService.changeTableHost(gameTblMemberKey.getTableId());
        }
    	resp.put("status",ResponseStatus.SUCCESS); 
    	resp.put("message", "You are successfully left the table."); 
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/deal/result", method = RequestMethod.POST)  
	public ResponseEntity<JSONObject> saveGameDealResult(@RequestBody DealResult dealResult){
		JSONObject resp=new JSONObject();
		
			
	    int[] wontTricks=dealResult.getWonTricks();
		
		int NSPoint=0;
		int EWPoint=0;
		
		 //declaredSide NS=0 EW=1
		
		int declaredSide=(dealResult.getPole()==0||dealResult.getPole()==2)?0:1;
	
		int basicPoint=0;
		
		int bonusPoint=gameService.getGameBonusPoints(dealResult.getNum()+dealResult.getSuit());
		
		
		int noOfOverTricks=wontTricks[declaredSide]-(dealResult.getNum()+6);
		
		if(noOfOverTricks>=0){
			basicPoint=(dealResult.getNum()+noOfOverTricks)* 10+ bonusPoint;			
		}else{
			//basicPoint=noOfOverTricks*10-bonusPoint/2;
			basicPoint=noOfOverTricks*20;
		}
		
						
		 basicPoint=dealResult.getDoubleCount()>0?basicPoint*dealResult.getDoubleCount()*2:basicPoint;
		
		
		 if(basicPoint>0){	
			 NSPoint=declaredSide==0?basicPoint:0;
			 EWPoint=declaredSide==1?basicPoint:0;	
		 }else{			
			 if(declaredSide==0){
				// NSPoint=basicPoint; 
				 EWPoint=-basicPoint;
			 }else{
				// EWPoint=basicPoint;
				 NSPoint=-basicPoint;				 
			 }
			
		 }
		 resp.put("NSPoint", NSPoint);
		 resp.put("EWPoint", EWPoint);
		 
		 //to increase the deal count +1 each every time when poin will be calculated
		 GameTable info=gameService.increaseGameTableDealCount(dealResult.getTableId());
				
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	/***********************************************************************************
	 * This function is used to generate the hand for four players
	 * @param tableId
	 */
	/*@RequestMapping(value="/table/generate/hand/{tableId}", method = RequestMethod.GET)    
	public void generateHand(@PathVariable(value = "tableId") int tableId){		
    	if(tableId!=0){
    		ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.DISTRIBUTE);	            
            chatMessage.setSender("SYSTEM");	           
            Hand hand=(new Deck()).getRandomHand();
            chatMessage.setContent("=============1236");
            messagingTemplate.convertAndSend("/topic/table/"+tableId, chatMessage);
		} 
	 }*/
}
