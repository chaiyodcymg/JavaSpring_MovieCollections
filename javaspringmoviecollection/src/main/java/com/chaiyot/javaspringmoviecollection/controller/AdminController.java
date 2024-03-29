package com.chaiyot.javaspringmoviecollection.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Autowired
	CategorieRepo catRepo;
	
	@Autowired
	MovieCategoryRepo movcatRepo;


	@GetMapping("/addmovies")
	public String home(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		List<Actors> actor = actorRepo.findAll();
		model.addAttribute("actorlist", actor);
		
		List<Categories> cat = catRepo.findAll();
		model.addAttribute("catlist", cat);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "addmovies";
	} 
	

	@GetMapping("/editmovies/{id}")
	public String edit(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response,
			@PathVariable Integer id) {
		List<MovCategory> movcat = movcatRepo.findAllID(id);
		model.addAttribute("movcatlist",movcat);
//		System.out.println(movcat);
		List<RoleActor> ract = maRepo.findAllID(id);
		model.addAttribute("listract", ract);
		
		
		Movies mov = movRepo.findById(id);
		model.addAttribute("movie" ,mov);
		model.addAttribute("movies_id",mov.getMovies_id());
		
		List<Actors> actor = actorRepo.findAll();
		model.addAttribute("actorlist", actor);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "editmovies";
	}
	
	
	@GetMapping("/deletemovies/{id}")
	public String delete(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response,
			@PathVariable Integer id) {
		
		 movRepo.deleteMovByID(id);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "redirect:/";
	}
	
	@GetMapping("/deleteactors/{id}")
	public String deleteactor(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response,
			@PathVariable Integer id) {
		
		actorRepo.deleteMovByID(id);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return "redirect:/actorlist";
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

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(admin.getPassword());
//		System.out.println("encodedPassword ="+encodedPassword );
		

		Admin ad = (Admin) repo.findByUsername(admin.getUsername());
		
		    if (ad  == null) {
				System.out.println("null");

				return "redirect:/admin/login";
		    }
		   
		    if( encoder.matches(admin.getPassword(), ad.getPassword())) {

				session.setAttribute("session", ad.getAdmin_id());
				return "redirect:/";
		    }else {
		    	System.out.println("null");
				return "redirect:/admin/login";
		    }
		    
		   
		
	

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request,Model model, HttpSession session) {
//		  session.setAttribute("user", "pack");
//	      String myAttribute = (String) session.getAttribute("session");
//	      System.out.println( myAttribute );
		 String referer = request.getHeader("referer");
		session.removeAttribute("session");
		return "redirect:/";
	}
}
