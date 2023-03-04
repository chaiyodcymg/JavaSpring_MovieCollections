package com.chaiyot.javaspringmoviecollection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MovieActors {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	
	@ManyToOne
	@JoinColumn(name = "actors_id")
	private Actors actors_id;
	
	@ManyToOne
	@JoinColumn(name = "movies_id")
	private Movies movies_id;
	
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Actors getActors_id() {
		return actors_id;
	}

	public void setActors_id(Actors actors_id) {
		this.actors_id = actors_id;
	}

	public Movies getMovies_id() {
		return movies_id;
	}

	public void setMovies_id(Movies movies_id) {
		this.movies_id = movies_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
