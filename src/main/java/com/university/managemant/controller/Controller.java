package com.university.managemant.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.managemant.model.Request;
import com.university.managemant.model.Student;
import com.university.managemant.model.Teacher;
import com.university.managemant.requestRespondseHandler.JwtRequest;
import com.university.managemant.requestRespondseHandler.RequestDto;
import com.university.managemant.requestRespondseHandler.UpdateDto;
import com.university.managemant.service.AdminService;
import com.university.managemant.service.JwtUserDetailsService;
import com.university.managemant.service.RequestService;
import com.university.managemant.service.StudentService;
import com.university.managemant.service.TeacherService;
import com.university.managemant.service.UserService;

@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
	private JwtUserDetailsService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private HttpSession httpSession;

	
	@GetMapping("/welcome")
	public String welCome() {
		return "welcome to 24 codding challenge";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) {
		String email = authenticationRequest.getEmail();
		String password = authenticationRequest.getPassword();
		boolean check = userService.checkLoginCredential(email, password,httpSession);
		String userType = userService.getUserType();
		Map<String, Object> response = new HashMap<>();
		response.put("userType", userType);
		
		if(check) {
			if(userType.equalsIgnoreCase("Teacher")) {
				response.put("userDetails", teacherService.getSingleTeachersInfo(email));
				return ResponseEntity.ok(response);
			}else if(userType.equalsIgnoreCase("Student")){
				response.put("userDetails", studentService.getOneStudentsInfo(email));
				return ResponseEntity.ok(response);
			}else if(userType.equalsIgnoreCase("Admin")){
				response.put("userDetails", adminService.getAdminsInfo(email));
				return ResponseEntity.ok(response);
			}else {
				response.put("userDetails", "this user type dont exist yet!!");
				return ResponseEntity.ok(response);
			}
		}else {
			response.put("userDetails", "Not authorised");
			return ResponseEntity.ok(response);
		}	
	}
	
	@PostMapping("/deactiveAccount")
	public ResponseEntity<Map<String, Object>> deactiveUserAccount(@RequestParam("email") String email){
		Map<String, Object> response = new HashMap<>();
		String loggedInUserEmail = userService.getUserEmailFromSession(httpSession);
        if(email.equals("admin@gmail.com")) {
        	response.put("message", "admin cannot be deactivated");
        	response.put("status", HttpStatus.FORBIDDEN.value());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}else if(loggedInUserEmail.equalsIgnoreCase("admin@gmail.com")){
			response.put("message", userService.deactiveAccount(email));
        	response.put("status", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response.put("message", "you dont have the permission to deactivate!!");
        	response.put("status", HttpStatus.FORBIDDEN.value());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
			
		}
	}
	
	
	@PostMapping("/activateAccount")
	public ResponseEntity<Map<String, Object>> activateUserAccount(@RequestParam("email") String email){
		Map<String, Object> response = new HashMap<>();
		String loggedInUserEmail = userService.getUserEmailFromSession(httpSession);
        if(loggedInUserEmail.equalsIgnoreCase("admin@gmail.com")){
			response.put("message", userService.activateAccount(email));
        	response.put("status", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response.put("message", "you dont have the permission to activate!!");
        	response.put("status", HttpStatus.FORBIDDEN.value());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PostMapping("/requestForAdvisor")
	public ResponseEntity<?> advisorRequest(@RequestBody RequestDto request){
		return ResponseEntity.ok(requestService.addRequest(request.getStudentEmail(), request.getTeacherEmail(), "pending"));
	}
	
	
	@PostMapping("/approveRequest")
	public ResponseEntity<?> approveAdvisorRequest(@RequestParam("studentEmail") String studentEmail){
		Student student = studentService.getOneStudentsInfo(studentEmail);
		Request request = requestService.approveRequest(studentEmail);
		Teacher teacher = teacherService.addApprovedStudentInfoToTeacher(request.getTeacherEmail(), student);
		
		return ResponseEntity.ok(teacher);
	}
	
	
	@GetMapping("/getAllTeachers")
	public ResponseEntity<?> findAllteachers(){
//		System.out.println("------>"+userService.getUserEmailFromSession(httpSession));
//		if(userService.getUserEmailFromSession(httpSession).equals("admin@gmail.com")) {
			return ResponseEntity.ok(teacherService.getAllTeachersInfo());
//		}else {
//			return ResponseEntity.ok("you are not authorised to get those information");
//		} 
		
		
		
	}
	
	
	@GetMapping("/getAllStudents")
	public ResponseEntity<?> findAllStudents(){
//		if(userService.getUserEmailFromSession(httpSession).equals("admin@gmail.com")) {
			return ResponseEntity.ok(studentService.getAllStudentsInfo());
//		}else {
//			return ResponseEntity.ok("you are not authorised to get those information");
//		} 	
		
	}
	
	@GetMapping("/getTeacherWiseAllStudents")
	public ResponseEntity<?> findTeacherWiseAllStudents(@RequestParam("teacherEmail") String teacherEmail){
		String loggedInUserEmail = userService.getUserEmailFromSession(httpSession);
		if(loggedInUserEmail.equals(teacherEmail)) {
			return ResponseEntity.ok(teacherService.getTeacherWiseStudentList(teacherEmail));
		}else {
			return ResponseEntity.ok("you cannot get others student list");
		}
	}
	
	@PatchMapping("/removeStudentFromTeacher")
	public ResponseEntity<?> deleteStudentFromTeacher(@RequestParam("studentEmail") String studentEmail,
			@RequestParam("teacherEmail") String teacherEmail){
		
		String loggedInUserEmail = userService.getUserEmailFromSession(httpSession);
		if(loggedInUserEmail.equals(teacherEmail)) {
			studentService.removeAdvisor(studentEmail);
			requestService.cancelRequest(studentEmail);
			return ResponseEntity.ok(teacherService.getSingleTeachersInfo(teacherEmail));
		}else {
			return ResponseEntity.ok("you cannot delete other theacher's student");
		}
	}
	
	@PatchMapping("/update")
	public ResponseEntity<?> updateTeacherOrStudent(@RequestBody UpdateDto updateRequest){
		UpdateDto updateDto = updateRequest;
		if(updateDto.getUserType().equals("Student")) {
			return ResponseEntity.ok(studentService.updateStudent(updateDto.getEmail(), updateDto.getName(), updateDto.getPhoneNo(), updateDto.getDepartmentName()));
		}else if(updateDto.getUserType().equals("Teacher")) {
			return ResponseEntity.ok(teacherService.updateTeacher(updateDto.getEmail(), updateDto.getName(), updateDto.getPhoneNo(), updateDto.getDepartmentName()));
		}else {
			return ResponseEntity.ok("cannot update");
		}
	}
	
	
	
	

}
