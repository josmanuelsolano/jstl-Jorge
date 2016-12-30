package com.softtek.academy.jstl.service;

import java.util.List;

import com.softtek.academy.jstl.domain.model.User;
import com.softtek.academy.jstl.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository ;
	
	public UserService() {
		userRepository = new UserRepository();
	}

	public List<User> list(){
		List<User> users = userRepository.list();
		
		return users;
	}
	
	public Boolean update(User user){
		if (this.isValidUser(user)) {
			
			 userRepository.update(user);
			 
			return true;
		}
		
		return false;
	}
	
	public User findOne(String username){
		
		User user = userRepository.findOne(username);
		
		return user;
		
	}
	
	private Boolean isValidUser(final User user) {
			
			if (user.getUsername() == null 
					|| user.getPassword() == null 
					|| user.getName() == null 
					|| user.getUserRole().getId() == null 
					|| user.getActive() == null) {
				return false;
			}
			
			return true;
		}

}
