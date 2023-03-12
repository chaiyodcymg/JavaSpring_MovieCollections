package com.chaiyot.javaspringmoviecollection.model;

import java.sql.Date;
import java.time.LocalDate;

public class RoleActor {

	private int actors_id; 
	
	private String role; 
	private String ActorName;
	
	private String image;
	
	private Date birthday;
	
	private String gender;
	
	boolean deleted = false;

	
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getActors_id() {
		return actors_id;
	}
	public void setActors_id(int actors_id) {
		this.actors_id = actors_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getActorName() {
		return ActorName;
	}
	public void setActorName(String actorName) {
		ActorName = actorName;
	}
	
	

	
}
