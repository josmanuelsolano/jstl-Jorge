package com.softtek.academy.jstl.domain.model;

public class UserRole {
	
	private String id;
	private String description;
	
    public UserRole() {
		
	}
    
	public UserRole(String userRole){
		this.id = userRole;
	}
	
	public UserRole(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", description=" + description + "]";
	}
	
}
