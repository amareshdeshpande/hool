package com.hool.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hool.app.model.Enums.ResponseStatus;
import com.hool.app.model.Mail;
import com.hool.app.model.Member;
import com.hool.app.model.PasswordReset;
import com.hool.app.model.PasswordResetToken;
import com.hool.app.repository.PasswordResetTokenRepository;
import com.hool.app.service.EmailService;
import com.hool.app.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired  private MemberService memberService;
    @Autowired  private PasswordResetTokenRepository tokenRepository;
    @Autowired  private EmailService emailService;	
    @Autowired private PasswordEncoder oauthClientPasswordEncoder;
    
	@SuppressWarnings("unchecked")
	@RequestMapping(value="", method = RequestMethod.GET)    
	public ResponseEntity<JSONObject> listUser(){
		JSONObject resp=new JSONObject();
        List<Member> members= memberService.findAll(); 
        resp.put("status",ResponseStatus.SUCCESS);
		resp.put("member", members);
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getMemberByUsername(@PathVariable(value = "username") String username){    
		
		JSONObject resp=new JSONObject();
		Member member=memberService.findMemberByLoginId(username);		
		if(member==null){
			resp.put("status",ResponseStatus.INFO);
			resp.put("message", "No member found for username "+username);			
		}else{
			resp.put("status",ResponseStatus.SUCCESS);
			member.setPassword("");
			resp.put("member",member);
		}
		return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
    }


    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> create(@RequestBody Member member){    
    	JSONObject resp=new JSONObject();
    	if(memberService.findMemberByLoginId(member.getUserName())==null){ 
    		member.setImageName("tiger.svg");
    		Member mbrData=  memberService.saveMember(member);    		
    		resp.put("status",ResponseStatus.SUCCESS);
			resp.put("member", mbrData);
			resp.put("message", "Account created successfully!");
    	}
    	else{
    		resp.put("status",ResponseStatus.INFO);
			resp.put("message", "Account allready exists with username "+member.getUserName());		
    	}    	
    	return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);    	
    }   
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> delete(@PathVariable(value = "id") Long id){
    	JSONObject resp=new JSONObject();
    	memberService.delete(id);
    	resp.put("status",ResponseStatus.SUCCESS);
  		resp.put("message", "Member removed successfully!");
  		return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
    }
    
  
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/password/recovery/request", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> passwordRecoveryRequest(@RequestBody String  email,HttpServletRequest request){
    	JSONObject resp=new JSONObject();

        Member member = memberService.findMemberByEmail(email);
        if (member == null){
        	resp.put("status",ResponseStatus.SUCCESS);
      		resp.put("message", "We could not find an account for that e-mail address.");
        	return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
        
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setMember(member);
        token.setExpiryDate(10);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(member.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("member", member);
        model.put("signature", "HOOL");
        
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
        model.put("resetUrl", url + "/#!/reset/password/"+token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
        
        resp.put("model", model);
        resp.put("status",ResponseStatus.SUCCESS);
  		resp.put("message", "Password reset link has been sent to your registered email.");       	
    	
    	return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
    	
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/check/validity/{token}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> checkTokenValidity(@PathVariable String token){
    	JSONObject resp=new JSONObject();
    	PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
        	resp.put("status",ResponseStatus.ERROR);
      		resp.put("message", "Could not find password reset token.");   
          
        } else if (resetToken.isExpired()){
        	resp.put("status",ResponseStatus.ERROR);
      		resp.put("message", "Token has expired, please request a new password reset.");   
            
        } else {
        	resp.put("status",ResponseStatus.SUCCESS);      		
      		resp.put("token", resetToken.getToken());
        }        
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);    	
    }
    
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/reset/password", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> resetPassword(@RequestBody PasswordReset passwordReset){
    	JSONObject resp=new JSONObject();
    	PasswordResetToken token = tokenRepository.findByToken(passwordReset.getToken());
        if (token != null){
        	Member user = token.getMember();
            String updatedPassword = oauthClientPasswordEncoder.encode(passwordReset.getPassword());
            memberService.updatePassword(updatedPassword, user.getId());
            tokenRepository.delete(token);
            
        	resp.put("status",ResponseStatus.SUCCESS);
      		resp.put("message", "Password has been changed successfully.");        
        } 
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);    	
    }
    
}
