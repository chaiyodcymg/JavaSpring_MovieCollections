package com.chaiyot.javaspringmoviecollection.controller;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    private final static Path rootLocation = Paths.get("static/img");

    public static  Boolean store(MultipartFile img,String Ranstr) {
        try {
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        	
			String saveimg = new File("").getAbsolutePath()+"/src/main/resources/static/img/" + Ranstr;
			Path path = Paths.get(saveimg);
			Files.copy(img.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
        	System.out.println("rootLocation ="+rootLocation);
        	return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file " + img.getOriginalFilename(), e);
        }
    }
}