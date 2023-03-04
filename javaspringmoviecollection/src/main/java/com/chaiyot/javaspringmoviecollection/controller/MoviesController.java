package com.chaiyot.javaspringmoviecollection.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chaiyot.javaspringmoviecollection.model.Image;
import com.chaiyot.javaspringmoviecollection.model.ImageRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/image")
public class MoviesController {
	
	@Autowired
	private ImageRepo imgRepo;

	@PostMapping("/uploadimage")
	public String uploadimage(@RequestParam MultipartFile img) {
		
//		Image im = new Image();
//		im.setImagename(img.getOriginalFilename());
//		 
//		Image uploadimg = imgRepo.save(im);
//		if (uploadimg != null) {
			try {
				
				String Ranstr = RandomString.getAlphaNumericString(20) + GetExtensionFile.GetEx(img.getOriginalFilename());
				String saveimg = new File("").getAbsolutePath()+"/src/main/resources/static/img/" + Ranstr;
				Path path = Paths.get(saveimg);
				Files.copy(img.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				Image im = new Image();
				im.setImagename("/img/" + Ranstr);
				Image uploadimg = imgRepo.save(im);
				System.out.println(path);
			
			} catch (Exception e) {
				
			}
//		}
		
	
		return "redirect:/admin/addmovies";
	}
}
	

