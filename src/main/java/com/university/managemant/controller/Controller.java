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
		boolean check = userService.checkLoginCredential(email, password);
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
		response.put("success", userService.deactiveAccount(email));
    	return ResponseEntity.status(HttpStatus.OK).body(response);

	}
	
	
	@PostMapping("/activateAccount")
	public ResponseEntity<Map<String, Object>> activateUserAccount(@RequestParam("email") String email){
		Map<String, Object> response = new HashMap<>();
			response.put("success", userService.activateAccount(email));
        	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/requestForAdvisor")
	public ResponseEntity<Map<String, Object>> advisorRequest(@RequestBody RequestDto request){
		Request fetchedRequest = requestService.addRequest(request.getStudentEmail(), request.getTeacherEmail(),request.getStudentName(), "pending");
		Map<String, Object> response = new HashMap<>();
		if(fetchedRequest!=null) {
			response.put("success", true);
		}else {
			response.put("success", false);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@PostMapping("/approveRequest")
	public ResponseEntity<Map<String, Object>> approveAdvisorRequest(@RequestParam("studentEmail") String studentEmail){
		Student student = studentService.getOneStudentsInfo(studentEmail);
		Request request = requestService.approveRequest(studentEmail);
		Teacher teacher = teacherService.addApprovedStudentInfoToTeacher(request.getTeacherEmail(), student);
		Map<String, Object> response = new HashMap<>();
		if(teacher!=null) {
			response.put("success", true);
		}else {
			response.put("success", false);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/getTeacerWiseRequestList")
	public ResponseEntity<?> getTeacherWiseRequest(@RequestParam("teacherEmail") String teacherEmail){
		return ResponseEntity.ok(requestService.getTeacherBasedRequests(teacherEmail));
	}
	
	
	@GetMapping("/getAllTeachers")
	public ResponseEntity<?> findAllteachers(){
		return ResponseEntity.ok(teacherService.getAllTeachersInfo());	
	}
	
	
	@GetMapping("/getAllStudents")
	public ResponseEntity<?> findAllStudents(){
		return ResponseEntity.ok(studentService.getAllStudentsInfo());
	}
	
	@GetMapping("/getTeacherWiseAllStudents")
	public ResponseEntity<?> findTeacherWiseAllStudents(@RequestParam("teacherEmail") String teacherEmail){
			return ResponseEntity.ok(teacherService.getTeacherWiseStudentList(teacherEmail));
	}
	
	@PatchMapping("/removeStudentFromTeacher")
	public ResponseEntity<Map<String, Object>> deleteStudentFromTeacher(@RequestParam("studentEmail") String studentEmail,
			@RequestParam("teacherEmail") String teacherEmail){
		Map<String, Object> response = new HashMap<>();
		
		studentService.removeAdvisor(studentEmail);
		requestService.cancelRequest(studentEmail);
		Teacher teacher = teacherService.getSingleTeachersInfo(teacherEmail);
		if(teacher!=null) {
			response.put("success", true);
		}else {
			response.put("success", false);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
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
	
	@PatchMapping("/resetPassword")
	public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam("email") String email, 
			@RequestParam("newPass") String newPass, @RequestParam("oldPass") String oldPass) {
		
		Map<String, Object> response = new HashMap<>();
		response.put("success", userService.resetPassword(email, newPass, oldPass));
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	
	
	

}
