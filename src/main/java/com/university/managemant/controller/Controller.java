package com.university.managemant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.university.managemant.model.Teacher;
import com.university.managemant.requestRespondseHandler.JwtRequest;
import com.university.managemant.service.StudentService;
import com.university.managemant.service.TeacherService;
import com.university.managemant.service.UserService;

@RestController
public class Controller {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/welcome")
	public String welCome() {
		return "welcome to 24 codding challenge";
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) {
		String email = authenticationRequest.getEmail();
		String password = authenticationRequest.getPassword();
		boolean check = userService.checkLoginCredential(email, password);
		String userType = userService.getUserType();
		if(check) {
			if(userType.equalsIgnoreCase("Teacher")) {
				return ResponseEntity.ok(teacherService.getOneTeachersInfo(email));
			}else {
				return ResponseEntity.ok(studentService.getOneStudentsInfo(email));
			}
		}else {
			return ResponseEntity.ok("Wrong pass");
		}
		
		
		
//		return "welcome Mr."+userType;
	}
	

}
