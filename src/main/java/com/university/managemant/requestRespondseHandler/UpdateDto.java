package com.university.managemant.requestRespondseHandler;


public class UpdateDto {
	
	private String name;
	private String email;
	private String phoneNo;
	private String departmentName;
	private String userType;
	
	
	public UpdateDto(){}
	
	public UpdateDto(String name, String email, String phoneNo, String departmentName,String userType) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.departmentName = departmentName;
		this.userType = userType;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	

}
