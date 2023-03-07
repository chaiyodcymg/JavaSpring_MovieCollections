package com.chaiyot.javaspringmoviecollection.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chaiyot.javaspringmoviecollection.model.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	@Autowired
	private MovieCategoryRepo movcatRepo;

	@PostMapping("/addmovies")
	public String addmovie(@RequestParam("img") MultipartFile img,@RequestParam("poster") MultipartFile poster,
			@RequestParam("moviename") String moviename, @RequestParam("category") List<Integer> categoryId,
			@RequestParam("description") String description, @RequestParam("role") List<String> role,
			@RequestParam("actorID") List<Integer> actorID, @RequestParam("yearmovie") String year, HttpServletRequest request,
			HttpSession session) {
//
//		for(String cat : category) {
//			System.out.println(cat);;
//		}
//		System.out.println(category);
		if (!img.getOriginalFilename().equals("") && !poster.getOriginalFilename().equals("")) {
			try {
				List<MovieCategory> movcat = new ArrayList<>();
				List<MoviesActors> movact = new ArrayList<>();
				int adsession = (int) request.getSession().getAttribute("session");
				String Ranstr = RandomString.getAlphaNumericString(20)
						+ GetExtensionFile.GetEx(img.getOriginalFilename());
				
				String RanstrPoster= RandomString.getAlphaNumericString(20)
						+ GetExtensionFile.GetEx(poster.getOriginalFilename());
				File savefile = new ClassPathResource("static/img").getFile();
//
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				Path pathPoster = Paths.get(savefile.getAbsolutePath() + File.separator + RanstrPoster);
				
				Files.copy(poster.getInputStream(), pathPoster, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				Movies movs = new Movies();
				Admin ad = new Admin();
				movs.setMoviename(moviename);
				movs.setDescription(description);
				movs.setReleaseYear(year);
				movs.setDeleted(false); 
				movs.setImage("img/" + Ranstr);
				movs.setPosterimage("img/" + RanstrPoster);
				ad.setAdmin_id(adsession);
				movs.setAdmin(ad);
				Movies saveMovie = movRepo.save(movs);
  
				for (int index = 0; index < role.size(); index++) {

					MoviesActors obmov = new MoviesActors();
					Actors act = new Actors();
					Movies mov = new Movies();

					act.setActors_id(actorID.get(index));
					mov.setMovies_id(saveMovie.getMovies_id());
					obmov.setActors(act);
					obmov.setRole(role.get(index));
					obmov.setMovies(mov);
//					System.out.println(act.getActors_id());
					movact.add(obmov);
//
				}

				movactRepo.save(movact);
				
				for(int index = 0; index < categoryId.size(); index++) {
					MovieCategory obmovcat = new MovieCategory();
					Movies movfcat = new Movies();
					Categories catfcat = new Categories();
				
				
					catfcat.setCategory_id(categoryId.get(index));
					movfcat.setMovies_id(saveMovie.getMovies_id());
					obmovcat.setCategories(catfcat);
					obmovcat.setMovies(movfcat);
					movcat.add(obmovcat);
				}
				movcatRepo.save(movcat);

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
			return "redirect:/admin/addmovies";
		}
		

		return "redirect:/";
	}

	@PostMapping("/addactor")
	public String addactor(@RequestParam MultipartFile img, HttpSession session,
			@RequestParam("Actorname") String Actorname, @RequestParam("birthday") LocalDate birthday, 
			@RequestParam("actorgender") String actorgender) {
		String Ranstr = RandomString.getAlphaNumericString(20) + GetExtensionFile.GetEx(img.getOriginalFilename());
//		System.out.println(img.getOriginalFilename().equals(""));
		System.out.println(Actorname);
		if (!img.getOriginalFilename().equals("")) {
			try {

				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				Actors act = new Actors();
				act.setImage("img/" + Ranstr);
				act.setActorName(Actorname);
				act.setBirthday(birthday);
				act.setGender(actorgender);
				Actors uploadimg = actRepo.save(act);

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}

		return "redirect:/admin/addmovies";
	}
	
	@PostMapping("/addactoredt")
	public String addactoredt(@RequestParam MultipartFile img, HttpSession session,
			@RequestParam("Actorname") String Actorname, HttpServletRequest request, @RequestParam("birthday") LocalDate birthday, 
			@RequestParam("actorgender") String actorgender ) {
		String Ranstr = RandomString.getAlphaNumericString(20) + GetExtensionFile.GetEx(img.getOriginalFilename());
//		System.out.println(img.getOriginalFilename().equals(""));
//		System.out.println(Actorname);
		if (!img.getOriginalFilename().equals("")) {
			try {

				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				Actors act = new Actors();
				act.setImage("img/" + Ranstr);
				act.setActorName(Actorname);
				act.setBirthday(birthday);
				act.setGender(actorgender);
				Actors uploadimg = actRepo.save(act);

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}
		 String referer = request.getHeader("referer");
		return "redirect:"+referer;
	}

	

	@PostMapping("/editmovies")
	public String editmovies(@RequestParam("img") MultipartFile img, HttpSession session,
			@RequestParam("moviename") String moviename, @RequestParam("category") List<Integer> categoryId,
			@RequestParam("description") String description, @RequestParam("role") List<String> role,
			@RequestParam("actorID") List<Integer> actorID, @RequestParam("movies_id") int movies_id, 
			@RequestParam("yearmovie") String year,@RequestParam("poster") MultipartFile poster,
			HttpServletRequest request) {

		if (!img.getOriginalFilename().equals("") && !poster.getOriginalFilename().equals("")) {
			try {
				List<MovieCategory> movcat = new ArrayList<>();
				List<MoviesActors> movact = new ArrayList<>();
				int adsession = (int) request.getSession().getAttribute("session");
				String Ranstr = RandomString.getAlphaNumericString(20)
						+ GetExtensionFile.GetEx(img.getOriginalFilename());
				
				String RanstrPoster= RandomString.getAlphaNumericString(20)
						+ GetExtensionFile.GetEx(poster.getOriginalFilename());
				File savefile = new ClassPathResource("static/img").getFile();
//
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);
				Path pathPoster = Paths.get(savefile.getAbsolutePath() + File.separator + RanstrPoster);
				
				Files.copy(poster.getInputStream(), pathPoster, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				
				Movies movs = movRepo.findById(movies_id);
				Admin ad = new Admin();
				movs.setMoviename(moviename);
				movs.setDescription(description);
				movs.setImage("img/" + Ranstr);
				movs.setPosterimage("img/" + RanstrPoster);
				movs.setReleaseYear(year);
				ad.setAdmin_id(adsession);
				movs.setAdmin(ad);
				Movies saveMovie = movRepo.save(movs);

				movactRepo.delete(movies_id);
				if (role.size() > 0) {

					for (int index = 0; index < role.size(); index++) {

						MoviesActors obmov = new MoviesActors();
						Actors act = new Actors();
						Movies mov = new Movies();

						act.setActors_id(actorID.get(index));
						mov.setMovies_id(movies_id);
						obmov.setActors(act);
						obmov.setRole(role.get(index));
						obmov.setMovies(mov);
//					System.out.println(role.get(index) + " " + actorID.get(index));
						movact.add(obmov);

					}

					movactRepo.save(movact);
					
					movcatRepo.delete(movies_id);
					for(int index = 0; index < categoryId.size(); index++) {
						MovieCategory obmovcat = new MovieCategory();
						Movies movfcat = new Movies();
						Categories catfcat = new Categories();
					
					
						catfcat.setCategory_id(categoryId.get(index));
						movfcat.setMovies_id(movies_id);
						obmovcat.setCategories(catfcat);
						obmovcat.setMovies(movfcat);
						movcat.add(obmovcat);
					}
					movcatRepo.save(movcat);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			try {

//				System.out.println(movies_id);
				List<MovieCategory> movcat = new ArrayList<>();
				List<MoviesActors> movact = new ArrayList<>();
				Movies movs = movRepo.findById(movies_id);
				movs.setMoviename(moviename);
				movs.setDescription(description);
				Movies saveMovie = movRepo.save(movs);

				movactRepo.delete(movies_id);
				if (role != null) {
					for (int index = 0; index < role.size(); index++) {

						MoviesActors obmov = new MoviesActors();
						Actors act = new Actors();
						Movies mov = new Movies();

						act.setActors_id(actorID.get(index));
						mov.setMovies_id(movies_id);
						obmov.setActors(act);
						obmov.setRole(role.get(index));
						obmov.setMovies(mov);
//					System.out.println(role.get(index) + " " + actorID.get(index));
						movact.add(obmov);

					}

					movactRepo.save(movact);
					
					movcatRepo.delete(movies_id);
					for(int index = 0; index < categoryId.size(); index++) {
						MovieCategory obmovcat = new MovieCategory();
						Movies movfcat = new Movies();
						Categories catfcat = new Categories();
					
					
						catfcat.setCategory_id(categoryId.get(index));
						movfcat.setMovies_id(movies_id);
						obmovcat.setCategories(catfcat);
						obmovcat.setMovies(movfcat);
						movcat.add(obmovcat);
					}
					movcatRepo.save(movcat);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
//		

		return "redirect:/";
	}
}
