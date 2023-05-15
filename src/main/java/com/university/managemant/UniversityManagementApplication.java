package com.university.managemant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.university.managemant.requestRespondseHandler.UserDto;
import com.university.managemant.service.AdminService;
import com.university.managemant.service.JwtUserDetailsService;

@SpringBootApplication
public class UniversityManagementApplication {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(UniversityManagementApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		String defaultAdminEmail = "admin@gmail.com";
		if(!userDetailsService.checkAdminExist(defaultAdminEmail)) {
			UserDto user = new UserDto();
			user.setEmail(defaultAdminEmail);
			user.setPassword("admin");
			user.setUserType("Admin");
			user.setActive(true);
			userDetailsService.createUserAccount(user);
			adminService.saveAdminProfile("Admin User", user.getEmail(), "+8801XXXXXXXXX");
		}
		
		
	}

}
