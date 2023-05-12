package com.university.managemant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Teacher;
import com.university.managemant.repository.TeacherRepository;

@Service
public class TeacherService {
	
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	
	public List<Teacher> getAllTeachersInfo(){
		return teacherRepository.findAll();
	}
	
	public Teacher getOneTeachersInfo(String email) {
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

}
