package com.university.managemant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String phoneNo;
	
	
	public Admin() {}


	public Admin(long id, String name, String email, String phoneNo) {
		super();
		this.adminId = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
	}


	public long getId() {
		return adminId;
	}


	public void setId(long id) {
		this.adminId = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	@Override
	public String toString() {
		return "Admin [id=" + adminId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + "]";
	}
	
	
	

}
