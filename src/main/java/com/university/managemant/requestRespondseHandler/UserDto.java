package com.university.managemant.requestRespondseHandler;
public class UserDto {
	
	private String name;
	private String phoneNo;
	private String email;
	private String password;
	private String userType;
	private String departmentName;
	private boolean isActive;
	
	
	public UserDto(){}
	public UserDto(String email, String password, String userType, String name, String phoneNo, String departmentName) {
		super();
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.isActive = true;
		this.name = name;
		this.phoneNo = phoneNo;
		this.departmentName = departmentName;
	}
	
	
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
    
	
	
}