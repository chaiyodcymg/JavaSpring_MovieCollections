package com.chaiyot.javaspringmoviecollection.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chaiyot.javaspringmoviecollection.model.Actors;
import com.chaiyot.javaspringmoviecollection.model.ActorsRepo;
import com.chaiyot.javaspringmoviecollection.model.MovieActors;
import com.chaiyot.javaspringmoviecollection.model.Movies;
import com.chaiyot.javaspringmoviecollection.model.MoviesActorsRepo;
import com.chaiyot.javaspringmoviecollection.model.MoviesRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/movie")
public class MoviesController {
	
	@Autowired
	private MoviesRepo movRepo;
	
	@Autowired
	private ActorsRepo actRepo;
	
	@Autowired
	private MoviesActorsRepo movactRepo;

	@PostMapping("/addmovies")
	public String addmovie(@RequestParam MultipartFile img, HttpSession session, @RequestParam("moviename") String moviename, 
			@RequestParam("category") String category, @RequestParam("description") String description, 
			@RequestParam("role") List<String> role, @RequestParam("actorID") List<Integer> actorID){
	

		
		String Ranstr = RandomString.getAlphaNumericString(20) + GetExtensionFile.GetEx(img.getOriginalFilename());
		System.out.println(img.getOriginalFilename().equals(""));
		if (!img.getOriginalFilename().equals("")) {
			try {
			
				
				File savefile = new ClassPathResource("static/img").getFile();
				
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				
				Files.copy(img.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
				
				Movies mov = new Movies();
				mov.setMoviename(moviename);
				mov.setCategory(category);
				mov.setDescription(description);
				mov.setImage("img/" + Ranstr);
				Movies saveMovie = movRepo.save(mov);
//			 System.out.println(uploadimg.getId());
				
				MovieActors obmov = new MovieActors();
				
				Actors act = new Actors();		
				mov.setMovies_id(saveMovie.getMovies_id());
				
				for(int index = 0 ; index < role.size() ; index++){
					obmov.setRole(role.get(index));
					act.setActors_id(actorID.get(index));
					obmov.setActors_id(act);
					mov.setMovies_id(mov.getMovies_id());
					obmov.setMovies_id(mov);
					movactRepo.save(obmov);
					
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}
		
	
		return "redirect:/";
	}

	
	@PostMapping("/addactor")
	public String addactor(@RequestParam MultipartFile img, HttpSession session, @RequestParam("Actorname") String Actorname){
		String Ranstr = RandomString.getAlphaNumericString(20) + GetExtensionFile.GetEx(img.getOriginalFilename());
//		System.out.println(img.getOriginalFilename().equals(""));
		System.out.println(Actorname);
		if (!img.getOriginalFilename().equals("")) {
			try {
			
				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				Files.copy(img.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
				

				Actors act = new Actors();
				act.setImage("img/"+Ranstr);
				act.setActorName(Actorname);
				Actors uploadimg = actRepo.save(act);

			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}
		
	
		return "redirect:/admin/addmovies";
	}
	
	@PostMapping("/addactoredit")
	public String addactoredit(@RequestParam MultipartFile img, HttpSession session, @RequestParam("Actorname") String Actorname){
		String Ranstr = RandomString.getAlphaNumericString(20) + GetExtensionFile.GetEx(img.getOriginalFilename());
//		System.out.println(img.getOriginalFilename().equals(""));
		System.out.println(Actorname);
		if (!img.getOriginalFilename().equals("")) {
			try {
			
				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				Files.copy(img.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
				

				Actors act = new Actors();
				act.setImage("img/"+Ranstr);
				act.setActorName(Actorname);
				Actors uploadimg = actRepo.save(act);

			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}
		
	
		return "redirect:/admin/editmovies";
	}
}
	

