package com.chaiyot.javaspringmoviecollection.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;



@Entity
public class Movies {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int movies_id; 
	
	private String image;
	
	private String moviename;
	
	private String category;
	
	private String description;
	

	public int getMovies_id() {
		return movies_id;
	}

	public void setMovies_id(int movies_id) {
		this.movies_id = movies_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	


}

