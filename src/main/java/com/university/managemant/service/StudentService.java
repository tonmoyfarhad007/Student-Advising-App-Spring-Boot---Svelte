package com.university.managemant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Student;
import com.university.managemant.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudentsInfo(){
		return studentRepository.findAll();
	}
	
	public Student getOneStudentsInfo(String email) {
		return studentRepository.findByEmail(email);
	}
	
	public void saveSingleStudentInfo(String name, String email, String phone, String deptName, boolean isActive) {
		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPhoneNo(phone);
		student.setDepartmentName(deptName);
		student.setActive(isActive);
		
		studentRepository.save(student);
	}

}
