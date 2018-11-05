package com.hool.app.service;

import java.util.List;
import com.hool.app.model.Member;


public interface MemberService {
	List<Member> findAll();
	Member findMemberById(long id);
	Member findMemberByLoginId(String username);
	int findIDByUserName(String loginID);
	String findUserNameByID(String memberId);
	Member findMemberByEmail(String email);
	Member saveMember(Member member);
    void delete(long id);
    Member updatePassword(String password, long userId);
    Member updateOnlineStatus(int status, String loginId);
}
