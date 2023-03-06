package com.chaiyot.javaspringmoviecollection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chaiyot.javaspringmoviecollection.model.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminRepo repo;

	@Autowired
	ActorsRepo actorRepo;

	@Autowired
	MoviesRepo movRepo;
	
	@Autowired
	MoviesActorsRepo maRepo;

	PasswordEncoder Encode;

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@GetMapping("/addmovies")
	public String home(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		List<Actors> actor = actorRepo.findAll();
		model.addAttribute("actorlist", actor);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "addmovies";
	}

	@GetMapping("/editmovies/{id}")
	public String edit(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response,
			@PathVariable Integer id) {
		List<RoleActor> ract = maRepo.findAllID(id);
		model.addAttribute("listract", ract);
		
		Movies mov = movRepo.findById(id);
		model.addAttribute("movie" ,mov);
		
		List<Actors> actor = actorRepo.findAll();
		model.addAttribute("actorlist", actor);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "editmovies";
	}

	@GetMapping("/login")
	public String login(Model model, HttpSession session, HttpServletResponse response) {
//		  session.setAttribute("user", "pack");
//	      String myAttribute = (String) session.getAttribute("user");
//	      System.out.println( myAttribute );
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "login";
	}

	@PostMapping("/login")
	public String adminlogin(Model model, HttpSession session, @ModelAttribute Admin admin) {
//		  session.setAttribute("user", "pack");
//	      String myAttribute = (String) session.getAttribute("user");
//	      System.out.println( myAttribute );
//		
//	  String encode =	passwordEncoder().encode(admin.getPassword());
//		
//	  System.out.println("Admin = "+encode );
//	  assertTrue(passwordEncoder().matches(encode, encode)); /

		Boolean status = false;
		Admin ad = (Admin) repo.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if (ad != null) {
			System.out.println("Admin = " + ad.getUsername() + " " + ad.getPassword());
			status = true;
			session.setAttribute("session", ad.getId());
			model.addAttribute("status", status);
			return "redirect:/admin/addmovies";
		} else {
			System.out.println("null");
			model.addAttribute("status", status);
			return "redirect:/admin/login";
		}
//		

	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
//		  session.setAttribute("user", "pack");
//	      String myAttribute = (String) session.getAttribute("session");
//	      System.out.println( myAttribute );

		session.removeAttribute("session");
		return "redirect:/admin/login";
	}
}
