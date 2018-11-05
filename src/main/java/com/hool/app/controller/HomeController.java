package com.hool.app.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAutoConfiguration
public class HomeController {

   @RequestMapping(value = { "/"}, method = RequestMethod.GET)
   public String index() {
	
      return "index";
   }
   @RequestMapping(value = { "/index"}, method = RequestMethod.GET)
   public String index1() {
	 
      return "index";
   }
 

   @RequestMapping(value = { "/chat"}, method = RequestMethod.GET)
   public String chat() {
     
      return "chat";
   }
}

