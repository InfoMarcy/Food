package com.luxmart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(@RequestParam(value ="error", required=false) String error,
			            @RequestParam(value="logout", required=false) String logout, Model model){
							
		// check if the are any errors
		if(error != null){
			model.addAttribute("error", "Invalid username and password!");
		}
		
		if(logout != null){
			model.addAttribute("msg", "You have been logged out succesfully");
			
		}
		
		
		return "Login/login";
	}
	
	
	@RequestMapping("/Login")
	public String index(){
							
		
		
		return "redirect:/index";
	}

	
}