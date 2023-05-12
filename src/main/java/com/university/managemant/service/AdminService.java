package com.university.managemant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.managemant.model.Admin;
import com.university.managemant.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public void saveAdminProfile(String name, String email, String phone) {
		
		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setPhoneNo(phone);
		
		adminRepository.save(admin);
		
	}
	
	public Admin getAdminsInfo(String email) {
		return adminRepository.findByEmail(email);
		
	}
	
}
