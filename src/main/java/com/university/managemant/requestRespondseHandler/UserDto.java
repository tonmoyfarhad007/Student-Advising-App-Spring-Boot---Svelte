package com.university.managemant.requestRespondseHandler;
public class UserDto {
	
	private String email;
	private String password;
	private String userType;
	private boolean isActive;
	
	
	public UserDto(){}
	public UserDto(String email, String password, String userType) {
		super();
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.isActive = true;
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