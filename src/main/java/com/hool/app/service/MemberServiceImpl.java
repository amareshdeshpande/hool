package com.hool.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hool.app.model.Member;
import com.hool.app.model.Role;
import com.hool.app.repository.MemberRepository;
import com.hool.app.repository.RoleRepository;

@Service("memberService")
public class MemberServiceImpl implements MemberService, UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
    private RoleRepository roleRepository;
    
	@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;	
	private PasswordEncoder oauthClientPasswordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUserName(username);		
		if(member == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(member.getUserName(), member.getPassword(), getAuthority());
	}
	
	@Override
	public Member findMemberById(long id) {
		return memberRepository.getOne(id);
	}

	@Override
	public Member findMemberByLoginId(String username) {
		return memberRepository.findByUserName(username);
	}

	@Override
	public Member findMemberByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	@Override
	public Member saveMember(Member member) {
		member.setPassword(oauthClientPasswordEncoder.encode(member.getPassword()));
		member.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        member.setRoles(new HashSet<Role>(Arrays.asList(userRole)));		
		return memberRepository.save(member);
	}

	@Override
	public List<Member> findAll() {
		List<Member> list = new ArrayList<>();
		memberRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		memberRepository.delete(id);
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public int findIDByUserName(String loginID) {
		return memberRepository.findIDByUserName(loginID);
		 
	}
	
	@Override
    public Member updatePassword(String password, long userId) {
		Member member=memberRepository.findOne((long) userId);
		if(member!=null){
			member.setPassword(password);	
			return memberRepository.saveAndFlush(member);
		}
		return member;		
    }
	
	@Override
    public Member updateOnlineStatus(int status, String loginId) {
		Member member = memberRepository.findByUserName(loginId);	
		System.out.println(member.getUserName()+"---Password----"+member.getPassword());
		if(member!=null){
			member.setOnline(status);			
			return memberRepository.saveAndFlush(member);
		}
		return member;		
    }

	@Override
	public String findUserNameByID(String memberId) {
		return memberRepository.findUserNameById(memberId);
	}
}
