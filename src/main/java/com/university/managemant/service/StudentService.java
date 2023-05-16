package com.university.managemant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Student;
import com.university.managemant.model.Teacher;
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
	
	public void updateStudentInfo(String studentEmail, Teacher teacher) {
		Student student = studentRepository.findByEmail(studentEmail);
		student.setTeacher(teacher);
		
		studentRepository.save(student);
	}
	
	public List<Student> getTeacherWiseStudentList(Teacher teacher){
		List<Student> studentList = studentRepository.findByTeacher(teacher);
		return studentList;
		
	}
	
	public void removeAdvisor(String email) {
		Student student = studentRepository.findByEmail(email);
		student.setTeacher(null);
		studentRepository.save(student);
	}

	public void deactiveStudent(String email) {
		Student student = studentRepository.findByEmail(email);
		student.setActive(false);
		studentRepository.save(student);
		
	}
	public void activateStudent(String email) {
		Student student = studentRepository.findByEmail(email);
		student.setActive(true);
		studentRepository.save(student);
		
	}
	
	public Student updateStudent(String email, String name, String phoneNo, String departmentName) {
		Student student = studentRepository.findByEmail(email);
		student.setName(name);
		student.setPhoneNo(phoneNo);
		student.setDepartmentName(departmentName);
		return studentRepository.save(student);
		
	}

}
