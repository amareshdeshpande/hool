package com.hool.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UtilityController {
	 
	@RequestMapping("/reset-password")
  
    public String resetPassword(@RequestParam("token") String token) {
    	System.out.println(token);
        return "reset-password";
    }
    

}
