package com.chaiyot.javaspringmoviecollection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "movies_actors")
@Entity
public class MoviesActors {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "movies_id")
	    private Movies movies;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "actors_id")
	    private Actors actors;

	    private String role;

		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Movies getMovies() {
			return movies;
		}

		public void setMovies(Movies movies) {
			this.movies = movies;
		}

		public Actors getActors() {
			return actors;
		}

		public void setActors(Actors actors) {
			this.actors = actors;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

}
