package com.hool.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hool.app.misc.Direction;
import com.hool.app.model.BonusPointMaster;
import com.hool.app.model.DealHand;
import com.hool.app.model.Enums;
import com.hool.app.model.GameTable;
import com.hool.app.model.GameTableMember;
import com.hool.app.model.GameTableMemberKey;
import com.hool.app.model.HandStats;
import com.hool.app.model.Member;
import com.hool.app.model.Enums.MemberType;
import com.hool.app.model.Enums.MessageType;
import com.hool.app.repository.GameTableRepository;
import com.hool.app.repository.MemberRepository;
import com.hool.app.repository.BonusPointMasterRepository;
import com.hool.app.repository.GameTableMemberRepository;

@Service("gameService")
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameTableRepository gameRepository;	
	@Autowired
	private GameTableMemberRepository  gameTableMemberRepository;	
	@Autowired 
	private MemberRepository memberRepository; 	
	@Autowired 
	private BonusPointMasterRepository bonusPointMasterRepository;
	@Autowired
	private MemberService memberService;
	
	
	@Override
	public GameTable createGameTable(GameTable game, int gameTableHostId) {		
		GameTable gameTable = getGameTableByHostId(gameTableHostId);
		if(gameTable==null){
			game.setIsTableActive(1);
			return gameRepository.save(game);
		}
		else{
			gameTable.setHistory(game.getHistory());
			gameTable.setKibitzer(game.getKibitzer());
			gameTable.setPrivacy(game.getPrivacy());
			gameTable.setTurnTime(game.getTurnTime());
			gameTable.setIsTableActive(1);
			return gameRepository.saveAndFlush(gameTable);
		}
	}

	@Override
	public GameTable updateGameTable(GameTable game) {
		GameTable gameTb=gameRepository.findOne(game.getId());
		if(gameTb!=null){
			gameTb.setHistory(game.getHistory());
			gameTb.setKibitzer(game.getKibitzer());
			gameTb.setPrivacy(game.getPrivacy());
			gameTb.setTurnTime(game.getTurnTime());		
		}
		return gameRepository.saveAndFlush(gameTb);
	}	
	
	@Override
	public GameTable increaseGameTableDealCount(int tableId) {
		GameTable gameTb=gameRepository.getOne(tableId);
		if(gameTb!=null){			
				gameTb.setDealCount(gameTb.getDealCount()+1);
		}
		return gameRepository.saveAndFlush(gameTb);		
	}
	
	@Override
	public GameTable changeNoOfMemberAtGameTable(int tableId, MemberType memberType, MessageType messageType) {
		GameTable gameTbl=gameRepository.findOne(tableId);
		
		 if(gameTbl!=null){	
			//if(gameTbl.getNoOfPlayer()<4){
				if(MemberType.KIBITZER==memberType){
					if(MessageType.JOIN==messageType){
						gameTbl.setNoOfKibitzer(gameTbl.getNoOfKibitzer()+1);
					}
					else if(MessageType.LEAVE==messageType){
						gameTbl.setNoOfKibitzer(gameTbl.getNoOfKibitzer()-1);
					}
					
				}else if(Enums.MemberType.PLAYER==memberType){
					if(MessageType.JOIN==messageType){
						gameTbl.setNoOfPlayer(gameTbl.getNoOfPlayer()+1);
						gameTbl.setNoOfKibitzer(gameTbl.getNoOfKibitzer()-1);
						gameTbl.setDealCount(0);
					}
					else if(MessageType.LEAVE==messageType){
						gameTbl.setNoOfPlayer(gameTbl.getNoOfPlayer()-1);
					}
				}
				return gameRepository.saveAndFlush(gameTbl);
		   //}
		}
		 
		 return gameTbl;
		
	}

	@Override
	public List<GameTable> getGameTables() {		// TODO Auto-generated method stub
		
		List<GameTable> list = new ArrayList<>();
		//gameRepository.findAll().iterator().forEachRemaining(System.out::println);
		//gameRepository.findAll().iterator().forEachRemaining(list::add);
		gameRepository.findAll().stream().filter(g->g.getIsTableActive()==1).forEach(list::add);
		return list;
		
	}

	@Override
	public GameTable getGameTableByHostId(int gameTableHostId) {		
		return gameRepository.findByHostId(gameTableHostId);
	}

	@Override
	public GameTable getGameTableById(int gameTableId) {
		return gameRepository.findOne(gameTableId);
	}

	@Override
	public int removeGameTable(int gameTableId) {
		gameRepository.delete(gameTableId);
		return 0;
	}

	@Override
	public Boolean joinGameTable(GameTableMember gameTableMember) {		
		GameTableMember gm=gameTableMemberRepository.saveAndFlush(gameTableMember);		
		if(gm!=null){		
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<GameTableMember> getGameTableMembers(int gameTableId) {			
		List<GameTableMember> list = new ArrayList<>();	
		list=gameTableMemberRepository.getGameTableMembers(gameTableId,"PLAYER");
		return list;
	}

	@Override
	public GameTableMember changeMemberTypeInGameTable(GameTableMember gameMember) {
		try
		{			
			GameTableMemberKey gameTableMemberKey=new GameTableMemberKey(gameMember.getGameTableMemberKey().getTableId(), gameMember.getGameTableMemberKey().getMemberId());
			GameTableMember gameTableMember=gameTableMemberRepository.findOne(gameTableMemberKey);
			if(gameTableMember!=null){
				if(gameTableMember.getMemberType()==MemberType.KIBITZER){
					int gameTableId=gameTableMember.getGameTableMemberKey().getTableId();
					GameTable gmt=changeNoOfMemberAtGameTable(gameTableId, MemberType.PLAYER, MessageType.JOIN);
					System.out.println("Total game Table memeber =========== "+gmt.getNoOfPlayer());
					
				}
				gameTableMember.setMemberType(gameMember.getMemberType());
				gameTableMember.setPole(gameMember.getPole());
				gameTableMember.setJoinTime(new Date());
				
				return gameTableMemberRepository.saveAndFlush(gameTableMember);
			}
			return gameTableMember;
		}
		catch(Exception e)
		{			
			throw e;
		}
	}

	@Override
	public int removeMemberFromGameTable(int tableId, int memberId) 
	{
		try
		{		
			GameTableMemberKey gameTableMemberKey=new GameTableMemberKey(tableId, memberId);
			GameTableMember gameTableMember=gameTableMemberRepository.findOne(gameTableMemberKey);
			if(gameTableMember!=null){			
				gameTableMemberRepository.delete(gameTableMember);
				changeNoOfMemberAtGameTable(tableId, gameTableMember.getMemberType(), MessageType.LEAVE);
				return 1;
			}	
			return 0;
		}
		catch(Exception e)
		{			
			throw e;
		}
	}
	
	@Override
	public GameTableMember removeMemberFromGameTable(int memberId) {
		try
		{
			List<GameTableMember> lst = gameTableMemberRepository.getMemberGameTableInfo(memberId);
			if(lst.size() > 0 ){
				for(GameTableMember m : lst){
					gameTableMemberRepository.delete(m);
					changeNoOfMemberAtGameTable(m.getGameTableMemberKey().getTableId(), m.getMemberType(), MessageType.LEAVE);					
				}
			}
			//memberService.updateOnlineStatus(0,memberId);
			return lst.size() > 0 ? lst.get(0) : null;			
		}
		catch(Exception e)
		{			
			throw e;
		}		
	}	

	@Override
	public List<DealHand> generateHand(int gameTableId) 
	{
		try
		{
			Deck deck=new Deck();
			List<DealHand> lst=new ArrayList<DealHand>();
			DealHand obj=null;
			for (int m=0; m<4; m++){
				Hand hand= deck.getRandomHand();			
				String[] suits = { "C", "D", "H", "S" };
				String[] cards_print = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };	
				List<String> cards=new ArrayList<String>();
				for (int j = 3; j > -1; j--) {				
					for (int i = 12; i > -1; i--){
						if (hand.getCard(j * 13 + i)){
							cards.add(cards_print[i]+ suits[j]);						
							//System.out.print(cards_print[i]+ suits[j] +", ");						
						}	
					}		
				}			
				obj=new DealHand(m, Direction.toString(m), cards, new HandStats(cards));
				lst.add(obj);
		    }
			return lst;
		}
		catch(Exception e)
		{			
			throw e;
		}
	}

	
	@Override
	public boolean isMemberTableHost(int tableId, int memberId) {
		GameTable gameTable=gameRepository.findByIdAndHostId(tableId,memberId);
		//System.out.println("  Table finding "+gameTable);
		if(gameTable!=null) {
		  return true;	
		}else {
			return false;
		}
		
	}

	@Override
	public void changeTableHost(int tableId) {	
		List<GameTableMember> lst=gameTableMemberRepository.findTableRecord(tableId);
		GameTable table=gameRepository.findOne(tableId);
		if(lst.isEmpty()) {
			//table.setIsTableActive(0);
			//gameRepository.saveAndFlush(table);	
			gameRepository.delete(tableId);
		}else{
			Long newHostId=(long) lst.get(0).getGameTableMemberKey().getMemberId();	
			Member member=memberRepository.findOne(newHostId);	
			
			table.setHostId((int)member.getId());
			table.setHostName(member.getUserName());
			gameRepository.saveAndFlush(table);		
			
		}	
		
	}

	@Override
	public int getGameBonusPoints(String contract) {
		BonusPointMaster points = bonusPointMasterRepository.getOne(contract);		
		return points.getPoint();
	}

}
