/**
 * 
 */
package com.softtek.academy.jstl.domain.model;

/**
 * @author Toshiba
 *
 */
public class User {
	
	private String username;
	private String password;
	private String name;
	private UserRole userRole;
	private String active;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", userRole=" + userRole
				+ ", active=" + active + "]";
	}
	
	
	
	

}
