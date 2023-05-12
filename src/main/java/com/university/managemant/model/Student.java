package com.university.managemant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String phoneNo;
	
	private String departmentName;
	
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id",referencedColumnName = "teacherId", nullable = true)
	private Teacher teacher;

	public Student() {}
	
	public Student(long studentId, String name, String email, String phoneNo, String departmentName, boolean isActive,
			Teacher teacher) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.departmentName = departmentName;
		this.isActive = isActive;
		this.teacher = teacher;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", departmentName=" + departmentName + ", isActive=" + isActive + ", teacher=" + teacher + "]";
	}
	
	
	
	
	

}
