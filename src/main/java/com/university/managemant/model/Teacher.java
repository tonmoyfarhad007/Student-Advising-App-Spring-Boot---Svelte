package com.university.managemant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teacherId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String phoneNo;
	
	private String departmentName;
	
	private boolean isActive;
	
	@JsonIgnore 
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "teacher")
	private List<Student> students;
	
	public Teacher() {}

	public Teacher(long teacherId, String name, String email, String phoneNo, String departmentName, boolean isActive,
			List<Student> students) {
		super();
		this.teacherId = teacherId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.departmentName = departmentName;
		this.isActive = isActive;
		this.students = students;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", departmentName=" + departmentName + ", isActive=" + isActive + ", students=" + students + "]";
	}
	
	

}
