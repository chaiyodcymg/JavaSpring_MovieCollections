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

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chaiyot.javaspringmoviecollection.model.*;

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

	@PostMapping("/addmovies")
	public String addmovie(@RequestParam MultipartFile img, HttpSession session,
			@RequestParam("moviename") String moviename, @RequestParam("category") String category,
			@RequestParam("description") String description, @RequestParam("role") List<String> role,
			@RequestParam("actorID") List<Integer> actorID) {

		if (!img.getOriginalFilename().equals("")) {
			try {

				List<MoviesActors> movact = new ArrayList<>();
				String Ranstr = RandomString.getAlphaNumericString(20)
						+ GetExtensionFile.GetEx(img.getOriginalFilename());
				File savefile = new ClassPathResource("static/img").getFile();

				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);

				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				Movies movs = new Movies();
				movs.setMoviename(moviename);
				movs.setCategory(category);
				movs.setDescription(description);
				movs.setImage("img/" + Ranstr);
				Movies saveMovie = movRepo.save(movs);

				for (int index = 0; index < role.size(); index++) {

					MoviesActors obmov = new MoviesActors();
					Actors act = new Actors();
					Movies mov = new Movies();

					mov.setMoviename(moviename);
					mov.setCategory(category);
					mov.setDescription(description);
					mov.setImage("img/" + Ranstr);
					act.setActors_id(actorID.get(index));
					mov.setMovies_id(saveMovie.getMovies_id());
					obmov.setActors(act);
					obmov.setRole(role.get(index));
					obmov.setMovies(mov);
//					System.out.println(act.getActors_id());
					movact.add(obmov);

				}

				movactRepo.save(movact);

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
			return "redirect:/admin/addmovies";
		}
//		

		return "redirect:/";
	}

	@PostMapping("/addactor")
	public String addactor(@RequestParam MultipartFile img, HttpSession session,
			@RequestParam("Actorname") String Actorname) {
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
				Actors uploadimg = actRepo.save(act);

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}

		return "redirect:/admin/addmovies";
	}

	@PostMapping("/addactoredit")
	public String addactoredit(@RequestParam MultipartFile img, HttpSession session,
			@RequestParam("Actorname") String Actorname) {
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
				Actors uploadimg = actRepo.save(act);

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			session.setAttribute("msg", "กรุณาเลือกรูปภาพ!");
		}

		return "redirect:/admin/editmovies";
	}

	@PostMapping("/editmovies")
	public String editmovies(@RequestParam MultipartFile img, HttpSession session,
			@RequestParam("moviename") String moviename, @RequestParam("category") String category,
			@RequestParam("description") String description, @RequestParam("role") List<String> role,
			@RequestParam("actorID") List<Integer> actorID, @RequestParam("movies_id") int movies_id) {

		if (!img.getOriginalFilename().equals("")) {
			try {

				List<MoviesActors> movact = new ArrayList<>();
				String Ranstr = RandomString.getAlphaNumericString(20)
						+ GetExtensionFile.GetEx(img.getOriginalFilename());
				File savefile = new ClassPathResource("static/img").getFile();

				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + Ranstr);

				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				Movies movs = movRepo.findById(movies_id);
				movs.setMoviename(moviename);
				movs.setCategory(category);
				movs.setDescription(description);
				movs.setImage("img/" + Ranstr);
				Movies saveMovie = movRepo.save(movs);

				movactRepo.delete(movies_id);
				if (role.size() > 0) {

					for (int index = 0; index < role.size(); index++) {

						MoviesActors obmov = new MoviesActors();
						Actors act = new Actors();
						Movies mov = new Movies();

						mov.setMoviename(moviename);
						mov.setCategory(category);
						mov.setDescription(description);
						act.setActors_id(actorID.get(index));
						mov.setMovies_id(movies_id);
						obmov.setActors(act);
						obmov.setRole(role.get(index));
						obmov.setMovies(mov);
//					System.out.println(role.get(index) + " " + actorID.get(index));
						movact.add(obmov);

					}

					movactRepo.save(movact);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			try {

				System.out.println(movies_id);
				List<MoviesActors> movact = new ArrayList<>();
				Movies movs = movRepo.findById(movies_id);
				movs.setMoviename(moviename);
				movs.setCategory(category);
				movs.setDescription(description);
				Movies saveMovie = movRepo.save(movs);

				movactRepo.delete(movies_id);
				if (role != null) {
					for (int index = 0; index < role.size(); index++) {

						MoviesActors obmov = new MoviesActors();
						Actors act = new Actors();
						Movies mov = new Movies();

						mov.setMoviename(moviename);
						mov.setCategory(category);
						mov.setDescription(description);
						act.setActors_id(actorID.get(index));
						mov.setMovies_id(movies_id);
						obmov.setActors(act);
						obmov.setRole(role.get(index));
						obmov.setMovies(mov);
//					System.out.println(role.get(index) + " " + actorID.get(index));
						movact.add(obmov);

					}

					movactRepo.save(movact);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
//		

		return "redirect:/";
	}
}
