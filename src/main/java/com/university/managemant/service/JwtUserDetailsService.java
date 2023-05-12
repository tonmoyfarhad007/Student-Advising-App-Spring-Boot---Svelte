package com.university.managemant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.university.managemant.model.User;
import com.university.managemant.repository.StudentRepository;
import com.university.managemant.repository.UserRepository;
import com.university.managemant.requestRespondseHandler.UserDto;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private StudentService studentService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

	public User save(UserDto user) {
		System.out.println("------------------->"+user.getEmail()+" "+user.getUserType());
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setActive(true);
		newUser.setUserType(user.getUserType());
		
		return userRepository.save(newUser);
	}
	
	public void setAccount(UserDto user) {
		if(user.getUserType().equalsIgnoreCase("Teacher")) {
			teacherService.saveSingleTeacherInfo(null, user.getEmail(), null, null, true);
		}else if(user.getUserType().equalsIgnoreCase("Student")) {
			studentService.saveSingleStudentInfo(null, user.getEmail(), null, null, true);
		}
		
	}
	
}