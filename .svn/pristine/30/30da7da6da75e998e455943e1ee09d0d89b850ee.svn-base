package com.hool.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hool.app.model.GameTable;


@Repository("gameTableRepository")
public interface GameTableRepository extends JpaRepository<GameTable, Integer> {

	GameTable findByHostId(int hostId);

	GameTable findByIdAndHostId(int tableId, int memberId);

	//@Query("select g.hostId from GameTable g where g.id=?")
	//int findByHostId(int gameTableId);

	//GameTable findByIdAndHostId(int id,int hostId);
	
	
}
