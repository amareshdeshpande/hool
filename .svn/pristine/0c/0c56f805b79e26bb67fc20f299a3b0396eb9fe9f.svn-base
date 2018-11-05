package com.hool.app.service;

import java.util.List;

import com.hool.app.model.Enums.MemberType;
import com.hool.app.model.Enums.MessageType;
import com.hool.app.model.DealHand;
import com.hool.app.model.GameTable;
import com.hool.app.model.GameTableMember;
import com.hool.app.model.GameTableMemberKey;
import com.hool.app.model.GameTablePointHistory;

public interface GameService {
	List<GameTable> getGameTables();
	GameTable getGameTableByHostId(int gameTableHostId);
	GameTable getGameTableById(int gameTableId);
	GameTable createGameTable(GameTable game, int gameTableHostId);
	GameTable updateGameTable(GameTable game);
	GameTable changeNoOfMemberAtGameTable(int memberId, MemberType memberType, MessageType messageType);
	int removeGameTable(int gameTableId);
	Boolean joinGameTable(GameTableMember gameTableMember);
	List<GameTableMember> getGameTableMembers(int gameTableId);
	GameTableMember changeMemberTypeInGameTable(GameTableMember gameMember);
	int removeMemberFromGameTable(int gameTableId, int memberId);
	boolean isMemberTableHost(int tableId, int memberId);
	void changeTableHost(int tableId);
	GameTableMember removeMemberFromGameTable(int memberId);
	List<DealHand> generateHand(int gameTableId);
	
	//GameTable updateGameTable(GameTablePointHistory points);
	int getGameBonusPoints(String contract);
	GameTable increaseGameTableDealCount(int tableId);
}
