package com.university.managemant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.university.managemant.model.User;
import com.university.managemant.repository.UserRepository;

@Service
public class UserService {
	
	private String userType;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public boolean checkLoginCredential(String email, String password) {
		
		try {
			User user = userRepository.findByEmail(email);
			userType = user.getUserType();
			
			if(passwordEncoder.matches(password, user.getPassword()) && user.isActive()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getUserType() {
		return userType;
		
	}
	
	

}
