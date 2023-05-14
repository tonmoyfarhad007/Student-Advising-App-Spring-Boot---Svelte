package com.university.managemant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.university.managemant.model.User;
import com.university.managemant.repository.StudentRepository;
import com.university.managemant.repository.UserRepository;
import com.university.managemant.requestRespondseHandler.UserDto;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private String userType;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

	public User createUserAccount(UserDto user) {
		System.out.println("------------------->"+user.getEmail()+" "+user.getUserType()+"!!! "+user.getPassword());
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setActive(true);
		newUser.setUserType(user.getUserType());
		
		return userRepository.save(newUser);
	}
	
	public void setProfile(UserDto user) {
		if(user.getUserType().equalsIgnoreCase("Teacher")) {
			teacherService.saveSingleTeacherInfo(user.getName(), user.getEmail(), user.getPhoneNo(), user.getDepartmentName(), true);
		}else if(user.getUserType().equalsIgnoreCase("Student")) {
			studentService.saveSingleStudentInfo(user.getName(), user.getEmail(), user.getPhoneNo(), user.getDepartmentName(), true);
		}
		
	}
	
	public boolean checkAdminExist(String email) {
		User user = userRepository.findByEmail(email);
		if(user==null) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	public boolean checkLoginCredential(String email, String password) {
		
		try {
			User user = userRepository.findByEmail(email);
			userType = user.getUserType();
			
			if(passwordEncoder.matches(password, user.getPassword()) && user.isActive()) {
				httpSession.setAttribute("email", email);
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
	
	
	public String getUserEmailFromSession() {
		return (String) httpSession.getAttribute("email");
	}
	

	
	public boolean deactiveAccount(String email) {
		User user = userRepository.findByEmail(email);
		if(user!=null) {
			user.setActive(false);
			userRepository.saveAndFlush(user);
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean activateAccount(String email) {
		User user = userRepository.findByEmail(email);
		if(user!=null) {
			user.setActive(true);
			userRepository.saveAndFlush(user);
			return true;
		}else {
			return false;
		}
		
	}


	
}