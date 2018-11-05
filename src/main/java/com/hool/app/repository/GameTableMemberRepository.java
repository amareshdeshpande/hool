package com.hool.app.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hool.app.model.GameTable;
import com.hool.app.model.GameTableMember;
import com.hool.app.model.GameTableMemberKey;

public interface GameTableMemberRepository extends JpaRepository<GameTableMember, GameTableMemberKey> {
	
	@Query(value = "select * from game_table_member where table_id = ?1 and member_type=?2", nativeQuery = true)
	List<GameTableMember> getGameTableMembers(int tableId, String memberType);

	GameTableMember findTop1ByJoinTime(int i);
	
	@Query(value="select u from GameTableMember as u  where u.gameTableMemberKey.tableId=?1 order by u.joinTime")
	List<GameTableMember> findTableRecord(int tableId);

	@Query(value = "select * from game_table_member where member_id = ?1", nativeQuery = true)
	List<GameTableMember> getMemberGameTableInfo(int memberId);
}
