package com.chaiyot.javaspringmoviecollection.model;

import java.util.List;

public class ShowMovieCategory {

	private int movies_id;
	
	private String category;
	
	private String moviename;

	private String posterimage;
	
	
	public String getPosterimage() {
		return posterimage;
	}

	public void setPosterimage(String posterimage) {
		this.posterimage = posterimage;
	}

	public int getMovies_id() {
		return movies_id;
	}

	public void setMovies_id(int movies_id) {
		this.movies_id = movies_id;
	}



	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	
	
}
