package com.chaiyot.javaspringmoviecollection.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/admin")
public class MainController {
	@GetMapping("/")
	public String home(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "home";
	}
}
