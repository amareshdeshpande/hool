package com.hool.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hool.app.model.Member;


@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Long> {
	 Member findByEmail(String email);	
	 
	 @Query(value = "SELECT * FROM MEMBER WHERE LOGIN_ID = ?1", nativeQuery = true)
	 Member findByUserName(String username);
	 
	 @Query(value = "SELECT member_id FROM MEMBER WHERE LOGIN_ID = ?1", nativeQuery = true)
	 int findIDByUserName(String loginID);


	 @Query(value = "SELECT LOGIN_ID FROM MEMBER WHERE member_id = ?1", nativeQuery = true)
	 String findUserNameById(String memberId);	 
	
}

