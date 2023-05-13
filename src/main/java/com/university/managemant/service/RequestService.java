package com.university.managemant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Request;
import com.university.managemant.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	RequestRepository requestRepository;
	
	public Request addRequest(String studentEmail, String teacherEmail, String status) {
		Request request = new Request();
		
		request.setTeacherEmail(teacherEmail);
		request.setStudentEmail(studentEmail);
		request.setStatus(status);
		
		return requestRepository.save(request);
	}
	
	public Request approveRequest(String studentEmail) {
		Request request = requestRepository.findByStudentEmail(studentEmail);
		request.setStatus("approved");
		return requestRepository.saveAndFlush(request);
	}
	

}
