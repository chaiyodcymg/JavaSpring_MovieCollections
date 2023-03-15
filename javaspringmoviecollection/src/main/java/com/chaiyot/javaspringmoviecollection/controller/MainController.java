package com.chaiyot.javaspringmoviecollection.controller;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chaiyot.javaspringmoviecollection.model.Actors;
import com.chaiyot.javaspringmoviecollection.model.ActorsRepo;
import com.chaiyot.javaspringmoviecollection.model.CategorieRepo;
import com.chaiyot.javaspringmoviecollection.model.Categories;
import com.chaiyot.javaspringmoviecollection.model.MovieCategory;
import com.chaiyot.javaspringmoviecollection.model.MovieCategoryRepo;
import com.chaiyot.javaspringmoviecollection.model.MovieDetail;
import com.chaiyot.javaspringmoviecollection.model.Movies;
import com.chaiyot.javaspringmoviecollection.model.MoviesActorsRepo;
import com.chaiyot.javaspringmoviecollection.model.MoviesRepo;
import com.chaiyot.javaspringmoviecollection.model.RoleActor;
import com.chaiyot.javaspringmoviecollection.model.ShowMovieCategory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/admin")
public class MainController {
	@Autowired
	MoviesRepo repo;
	
	@Autowired
	ActorsRepo actrepo;
	
	@Autowired
	MovieCategoryRepo movcatRepo;
	
	@Autowired
	CategorieRepo catRepo;
	
	@Autowired
	MoviesActorsRepo movactRepo;
	
	@GetMapping("/")
	public String home(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		
//		List<Movies> mov = repo.findAll();
//		model.addAttribute("list", mov);
		List<Categories> cat = catRepo.findAll();
		model.addAttribute("listcat",cat);
		
		 List<ShowMovieCategory> movcat = movcatRepo.findAllMov();
		 model.addAttribute("listmovcat", movcat);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//		System.out.println(img);
		return "home";
	}
	
	
	@GetMapping("/movie_detail/{id}")
	public String detail(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response,
			@PathVariable Integer id) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		
		Object movdetail = movcatRepo.findDetail(id);
		model.addAttribute("detail", movdetail);
		
		List<RoleActor>  ra =movactRepo.findAllID(id);
		model.addAttribute("Listactor", ra);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//		System.out.println(img);
		return "movie_detail";
	}
	
	@GetMapping("/actor/{id}")
	public String actormovie(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response,
			@PathVariable Integer id) {
		List<Categories> cat = catRepo.findAll();
		model.addAttribute("listcat",cat);
		
		Actors act = actrepo.findById(id);
		model.addAttribute("name",act.getActorName());
		
		LocalDate today = LocalDate.now();
		LocalDate birthday = act.getBirthday();
		Period p = Period.between(birthday, today);
		model.addAttribute("age",p.getYears());
		
		if(act.getGender().equals("m")) {
			act.setGender("ชาย");
		}else {
			act.setGender("หญิง");
		}
		model.addAttribute("gender",act.getGender());
		model.addAttribute("image",	act.getImage());

	      Date sqlDate = Date.valueOf(act.getBirthday());
	        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM  yyyy", new Locale("en", "EN"));
	        String thaiDate = formatter.format(sqlDate);
		model.addAttribute("birthday",	thaiDate);
		List<ShowMovieCategory> actmov = movactRepo.findActormovie(id);
		model.addAttribute("actmovlist",actmov);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		return "actormovies";
	}
	
	@GetMapping("/actorlist")
	public String actorlist(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response) {
//		  session.setAttribute("myAttribute", "hello");
//	      String myAttribute = (String) session.getAttribute("myAttribute");
//	      System.out.println( myAttribute );
		
		List<Actors> actor = actrepo.findAll();
		model.addAttribute("actlist", actor);
		List<Categories> cat = catRepo.findAll();
		model.addAttribute("listcat",cat);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//		System.out.println(img);
		return "actorlist";
	}
//	public String getImage(Model model) {
//		List<Image> img = repo.findAll();
//		model.addAllAttributes("Listimg", img);
//		return "home";
//	}
}
