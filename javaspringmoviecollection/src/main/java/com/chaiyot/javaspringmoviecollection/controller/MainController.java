package com.chaiyot.javaspringmoviecollection.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chaiyot.javaspringmoviecollection.model.Movies;
import com.chaiyot.javaspringmoviecollection.model.MoviesRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/admin")
public class MainController {
	@Autowired
	MoviesRepo repo;
	
	@GetMapping("/")
	public String home(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		
		List<Movies> mov = repo.findAll();
		model.addAttribute("list", mov);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//		System.out.println(img);
		return "home";
	}
	
//	public String getImage(Model model) {
//		List<Image> img = repo.findAll();
//		model.addAllAttributes("Listimg", img);
//		return "home";
//	}
}
