package com.university.managemant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Request;
import com.university.managemant.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	RequestRepository requestRepository;
	
	public Request addRequest(String studentEmail, String teacherEmail, String studentName, String status) {
		Request request = new Request();
		request.setStudentName(studentName);
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
	
	public Request cancelRequest(String studentEmail) {
		Request request = requestRepository.findByStudentEmail(studentEmail);
		request.setStatus("canceled");
		return requestRepository.saveAndFlush(request);
	}
	
	public List<Request> getTeacherBasedRequests(String teacherEmail){
		List<Request> request = requestRepository.findByTeacherEmail(teacherEmail);
		return request;
		
	}
	

}
