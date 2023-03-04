package com.chaiyot.javaspringmoviecollection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Actors {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int actors_id; 
	
	String ActorName; 
	String image; 
	


	

	public int getActors_id() {
		return actors_id;
	}

	public void setActors_id(int actors_id) {
		this.actors_id = actors_id;
	}

	public String getActorName() {
		return ActorName;
	}

	public void setActorName(String actorName) {
		ActorName = actorName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
