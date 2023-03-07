package com.chaiyot.javaspringmoviecollection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "movies_categories")
@Entity
public class MovieCategory {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "movies_id")
	    private Movies movies;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "category_id")
	    private Categories categories;
	     
		
		boolean deleted = false;


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public Movies getMovies() {
			return movies;
		}


		public void setMovies(Movies movies) {
			this.movies = movies;
		}


		public Categories getCategories() {
			return categories;
		}


		public void setCategories(Categories categories) {
			this.categories = categories;
		}


		public boolean isDeleted() {
			return deleted;
		}


		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		
		
}
