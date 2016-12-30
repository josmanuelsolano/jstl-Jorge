package com.softtek.academy.jstl.service;

import java.util.List;

import com.softtek.academy.jstl.domain.model.UserRole;
import com.softtek.academy.jstl.repository.UserRoleRepository;

public class UserRoleService {
	
	UserRoleRepository userRoleRepository;

	public UserRoleService() {
		userRoleRepository = new UserRoleRepository();
	}
	
	public List<UserRole> list(){
		
		List<UserRole> userRoles = userRoleRepository.list();
		
		return userRoles;
		
	}
	
	

}
