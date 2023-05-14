package com.university.managemant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Student;
import com.university.managemant.model.Teacher;
import com.university.managemant.repository.StudentRepository;
import com.university.managemant.repository.TeacherRepository;

@Service
public class TeacherService {
	
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentService studentService;
	
	
	public List<Teacher> getAllTeachersInfo(){
		return teacherRepository.findAll();
	}
	
	public Teacher getSingleTeachersInfo(String email) {
		return teacherRepository.findByEmail(email);
	}
	
	public void saveSingleTeacherInfo(String name, String email, String phone, String deptName, boolean isActive) {
		Teacher teacher = new Teacher();
		teacher.setName(name);
		teacher.setEmail(email);
		teacher.setPhoneNo(phone);
		teacher.setDepartmentName(deptName);
		teacher.setActive(isActive);
		
		teacherRepository.save(teacher);
	}
	
	public Teacher addApprovedStudentInfoToTeacher(String teacherEmail,Student student) {
		Teacher teacher = teacherRepository.findByEmail(teacherEmail);
		ArrayList<Student> studentList = new ArrayList<>();
		studentList.add(student);
		teacher.setStudents(studentList);
		studentService.updateStudentInfo(student.getEmail(), teacher);
		
		return teacherRepository.save(teacher);
	}
	
	public List<Student> getTeacherWiseStudentList(String email){
		
		Teacher teacher = teacherRepository.findByEmail(email);
		return studentService.getTeacherWiseStudentList(teacher);
	}

}
