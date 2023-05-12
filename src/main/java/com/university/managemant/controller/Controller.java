package com.university.managemant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.university.managemant.model.Teacher;
import com.university.managemant.requestRespondseHandler.JwtRequest;
import com.university.managemant.service.AdminService;
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
	
	@Autowired
	private AdminService adminService;
	
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
			}else if(userType.equalsIgnoreCase("Student")){
				return ResponseEntity.ok(studentService.getOneStudentsInfo(email));
			}else if(userType.equalsIgnoreCase("Admin")){
				return ResponseEntity.ok(adminService.getAdminsInfo(email));
			}else {
				return ResponseEntity.ok("this user type dont exist yet!!");
			}
		}else {
			return ResponseEntity.ok("Not authorised");
		}
		
		
		
//		return "welcome Mr."+userType;
	}
	

}
